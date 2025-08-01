#include "test.h"
#include "mem.h"
#include "mem_internals.h"

#include <assert.h>
#include <stdio.h>

void test_heap_init_and_term() {
    printf("Running test_heap_init_and_term...\n");

    size_t initial_size = 4096;
    void* heap_start = heap_init(initial_size);

    assert(heap_start != NULL);

    heap_term();

    assert(region_is_invalid(&heap_region));

    printf("test_heap_init_and_term passed!\n");
}

void test_malloc_and_free() {
    printf("Running test_malloc_and_free...\n");

    heap_init(4096);

    size_t alloc_size = 100;
    void* ptr = _malloc(alloc_size);

    assert(ptr != NULL);

    _free(ptr);

    void* ptr2 = _malloc(alloc_size);
    assert(ptr == ptr2);

    _free(ptr2);
    heap_term();

    printf("test_malloc_and_free passed!\n");
}

void test_split_block() {
    printf("Running test_split_block...\n");

    heap_init(4096);

    size_t alloc_size = 100;
    struct block_header* block = (struct block_header*)_malloc(alloc_size);

    assert(block != NULL);

    size_t remaining_capacity = block->capacity.bytes - alloc_size - offsetof(struct block_header, contents);
    assert(remaining_capacity >= BLOCK_MIN_CAPACITY);

    _free(block);
    heap_term();

    printf("test_split_block passed!\n");
}

void test_merge_blocks() {
    printf("Running test_merge_blocks...\n");

    heap_init(8192);

    size_t alloc_size1 = 100;
    size_t alloc_size2 = 200;

    void* ptr1 = _malloc(alloc_size1);
    void* ptr2 = _malloc(alloc_size2);

    assert(ptr1 != NULL);
    assert(ptr2 != NULL);

    _free(ptr1);
    _free(ptr2);

    struct block_header* block = (struct block_header*)heap_region.addr;
    assert(try_merge_with_next(block));

    heap_term();

    printf("test_merge_blocks passed!\n");
}

void test_alloc_small_block() {
    printf("Running test_alloc_small_block...\n");

    heap_init(4096);

    size_t alloc_size = 10;
    void* ptr = _malloc(alloc_size);

    assert(ptr != NULL);

    _free(ptr);
    heap_term();

    printf("test_alloc_small_block passed!\n");
}

void test_alloc_large_block() {
    printf("Running test_alloc_large_block...\n");

    heap_init(4096);

    size_t alloc_size = 8192;
    void* ptr = _malloc(alloc_size);

    assert(ptr != NULL);

    _free(ptr);
    heap_term();

    printf("test_alloc_large_block passed!\n");
}

void test_reallocate_after_free() {
    printf("Running test_reallocate_after_free...\n");

    heap_init(4096);

    size_t alloc_size1 = 100;
    size_t alloc_size2 = 200;

    void* ptr1 = _malloc(alloc_size1);
    void* ptr2 = _malloc(alloc_size2);

    assert(ptr1 != NULL);
    assert(ptr2 != NULL);

    _free(ptr1);

    void* ptr3 = _malloc(alloc_size1);
    assert(ptr1 == ptr3);

    _free(ptr2);
    _free(ptr3);

    heap_term();

    printf("test_reallocate_after_free passed!\n");
}

void test_continuous_allocation() {
    printf("Running test_continuous_allocation...\n");

    heap_init(4096);

    void* blocks[10];
    size_t block_size = 200;

    for (int i = 0; i < 10; i++) {
        blocks[i] = _malloc(block_size);
        assert(blocks[i] != NULL);
    }

    for (int i = 0; i < 10; i++) {
        _free(blocks[i]);
    }

    heap_term();

    printf("test_continuous_allocation passed!\n");
}

void test_invalid_free() {
    printf("Running test_invalid_free...\n");

    heap_init(4096);

    void* invalid_ptr = (void*)0xdeadbeef;
    _free(invalid_ptr);

    heap_term();

    printf("test_invalid_free passed!\n");
}

void do_all_tests() {
    test_heap_init_and_term();
    test_malloc_and_free();
    test_split_block();
    test_merge_blocks();
    test_alloc_small_block();
    test_alloc_large_block();
    test_reallocate_after_free();
    test_continuous_allocation();
    test_invalid_free();
}