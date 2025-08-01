#ifndef MEM_H
#define MEM_H

#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <sys/mman.h>

#include "mem_internals.h"

#define HEAP_START ((void*)0x04040000)

void heap_term(void);
void* _malloc(size_t query);
void _free(void* mem);
void debug_heap(FILE* f);
void* heap_init(size_t initial);
bool try_merge_with_next(struct block_header *block);
extern struct region heap_region;

#endif // MEM_H