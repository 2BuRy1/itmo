#include "mem.h"
#include "mem_internals.h"
#include "util.h"
#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <unistd.h>

#define BLOCK_MIN_CAPACITY 24
#define ALLOC_PAGE_SIZE 4096
#define MAP_FIXED_NOREPLACE 0x100000



#ifndef MAP_ANONYMOUS
#define MAP_ANONYMOUS 0x20
#endif
extern inline block_size size_from_capacity(block_capacity cap);
extern inline block_capacity capacity_from_size(block_size sz);

struct region heap_region;


static void* block_after( struct block_header const* block )              {
    return  (void*) (block->contents + block->capacity.bytes);
}


static bool blocks_continuous(struct block_header const *fst, struct block_header const *snd) {
    bool continuous = (void *)snd == block_after(fst);
    return continuous;
}


static bool block_splittable(struct block_header *restrict block,
                             size_t query) {
    return block->is_free && query + offsetof(struct block_header, contents) +
                                     BLOCK_MIN_CAPACITY <=
                                 block->capacity.bytes;
}


static bool block_is_big_enough(size_t query, struct block_header* block) {
    return block->capacity.bytes >= query;
}

static void *map_pages(void const *addr, size_t length, int additional_flags) {
    return mmap((void *)addr, length, PROT_READ | PROT_WRITE,
                MAP_PRIVATE | MAP_ANONYMOUS | additional_flags, -1, 0);
}

static void block_init(void* addr, block_size size, struct block_header* next) {
    if (!addr) return;
    *((struct block_header *)addr) =
        (struct block_header){
            .next = next,
            .capacity = capacity_from_size(size),
            .is_free = true};
}

static size_t pages_count(size_t mem) {
    return mem / ALLOC_PAGE_SIZE + ((mem % ALLOC_PAGE_SIZE) > 0);
}
static size_t round_pages(size_t mem) {
    return ALLOC_PAGE_SIZE * pages_count(mem);
}
static size_t region_actual_size(size_t query) {
    return size_max(round_pages(query), REGION_MIN_SIZE);
}

struct block_search_result {
    enum { BSR_FOUND_GOOD_BLOCK, BSR_REACHED_END_NOT_FOUND, BSR_CORRUPTED } type;
    struct block_header* block;
};



static struct region alloc_region(void const *addr, size_t query) {
    size_t actual_size = region_actual_size(query);
    if (query == REGION_MIN_SIZE) {
        actual_size += ALLOC_PAGE_SIZE;;
    }

    block_capacity capacity =
        (block_capacity){actual_size - offsetof(struct block_header, contents)};
    bool extends = true;

    void *region_addr = map_pages(addr, actual_size, MAP_FIXED);
    if (region_addr == MAP_FAILED) {
        region_addr = map_pages(addr, actual_size, 0);
        if (region_addr == MAP_FAILED) {
            return REGION_INVALID;
        }
        extends = false;
    }

    block_init(region_addr, size_from_capacity(capacity), NULL);

    return (struct region){
        .addr = region_addr, .size = actual_size, .extends = extends};
}



void* heap_init(size_t initial) {
    const struct region region = alloc_region(HEAP_START, initial);
    if (region_is_invalid(&region)) {
        return NULL;
    }

    heap_region = region;

    return region.addr;
}


void heap_term( ) {

    struct block_header* first_block_in_region = (struct block_header*) HEAP_START;
    struct block_header* current_block = first_block_in_region;
    size_t current_region_size = 0;

    while(true){
        current_region_size += size_from_capacity(current_block->capacity ).bytes;


        while(blocks_continuous(current_block, current_block->next)){
            current_block = current_block->next;
            current_region_size += size_from_capacity(current_block->capacity ).bytes;
        }
        if(current_block->next == NULL){
            munmap(first_block_in_region, region_actual_size(current_region_size));
            break;
        }
        current_block = current_block->next;
        munmap(first_block_in_region, region_actual_size(current_region_size));

        current_region_size = 0;
        first_block_in_region = current_block;
    }





}







bool try_merge_with_next(struct block_header *block) {
    if (!block || !block->next || !block->is_free || !block->next->is_free) {
        return false;
    }

    if ((uint8_t *)block + size_from_capacity(block->capacity).bytes !=
       (uint8_t *)block->next)
        return false;

    block->capacity.bytes += size_from_capacity(block->next->capacity).bytes;
    block->next = block->next->next;

    return true;
}

static struct block_search_result find_good_or_last(
    struct block_header *restrict block, size_t sz) {
    struct block_header *last = NULL;
    bool allow_dirty = false;

    while (block) {
        if (block->capacity.bytes < BLOCK_MIN_CAPACITY) {
            return (struct block_search_result){.type = BSR_CORRUPTED, .block = block};
        }

        while (try_merge_with_next(block));

        if (block_is_big_enough(sz, block) && (block->is_free || allow_dirty)) {
            return (struct block_search_result){.type = BSR_FOUND_GOOD_BLOCK, .block = block};
        }

        if (block->is_free) {
            allow_dirty = true;
        }

        last = block;
        block = block->next;
    }

    return (struct block_search_result){.type = BSR_REACHED_END_NOT_FOUND, .block = last};
}

static bool split_if_too_big(struct block_header* block, size_t query) {
    if (!block_splittable(block, query)) {
        return false;
    }



    size_t remaining_capacity =  block->capacity.bytes - query - offsetof(struct block_header, contents);
    if (remaining_capacity >= BLOCK_MIN_CAPACITY) {
        struct block_header *new_block = (struct block_header *)(block->contents + query);
        block_init(new_block, (block_size){.bytes = remaining_capacity}, block->next);
        block_capacity new_capacity = (block_capacity){ block->capacity.bytes - query - offsetof(struct block_header, contents)};

        block_init(new_block, size_from_capacity(new_capacity), block->next);

        block->next = new_block;
        block->capacity.bytes = query;

        return true;
    }
    return false;
}


static struct block_search_result try_memalloc_existing(size_t query, struct block_header* block) {
    struct block_search_result result = find_good_or_last(block, query);

    if (result.type == BSR_FOUND_GOOD_BLOCK) {
        split_if_too_big(result.block, query);
        result.block->is_free = false;
    }

    return result;
}

static struct block_header* grow_heap(struct block_header* last, size_t query) {
    if (!last) {
        return NULL;
    }

    struct region new_region = alloc_region(block_after(last), query);
    if (region_is_invalid(&new_region)) {
        return NULL;
    }

    struct block_header *block = new_region.addr;
    last->next = block;

    if (last->is_free && new_region.extends) {
        try_merge_with_next(last);
        return last;
    }

    return block;
}
static struct block_header* memalloc(size_t query, struct block_header* start_block) {
    query = size_max(BLOCK_MIN_CAPACITY, query);

    struct block_search_result result = try_memalloc_existing(query, start_block);
    if (result.type == BSR_FOUND_GOOD_BLOCK) {
        return result.block;
    }

    struct block_header* last = result.block;
    struct block_header* new_block = grow_heap(last, query);
    if (new_block) {
        split_if_too_big(new_block, query);
        new_block->is_free = false;
        return new_block;
    }

    return NULL;
}

void* _malloc(size_t query) {
    if (query == 0 || region_is_invalid(&heap_region)) {
        return NULL;
    }

    struct block_header* block = memalloc(query, (struct block_header*)heap_region.addr);
    return block ? block->contents : NULL;
}

void _free(void *mem) {
    if (!mem) {
        return;
    }

    struct block_header *block = (struct block_header *)((uint8_t *)mem - offsetof(struct block_header, contents));
    block->is_free = true;

    while (block->next && try_merge_with_next(block));

    struct block_header *current = (struct block_header *)heap_region.addr;
    while (current && current->next != block) {
        current = current->next;
    }
    if (current && current->is_free) {
        try_merge_with_next(current);
    }
}