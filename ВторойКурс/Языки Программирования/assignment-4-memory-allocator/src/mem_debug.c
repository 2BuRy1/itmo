// mem_debug.c
#include <stdarg.h>
#include <stdio.h>

#include "mem.h"
#include "mem_internals.h"

void debug_struct_info(FILE* f, void const* address) {
    const struct block_header* header = address;
    fprintf(f, "%10p %10zu %8s   ", address, header->capacity.bytes, header->is_free ? "free" : "taken");
    size_t data_size = header->capacity.bytes;
    size_t to_print = data_size < 4 ? data_size : 4;
    for (size_t i = 0; i < to_print; ++i) {
        fprintf(f, "%02X ", (unsigned char)header->contents[i]);
    }
    fprintf(f, "\n");
}

void debug_heap(FILE* f) {
    if (region_is_invalid(&heap_region)) {
        fprintf(f, "Heap is not initialized.\n");
        return;
    }

    const struct block_header* header = (struct block_header*)heap_region.addr;
    fprintf(f, " --- Heap ---\n");
    fprintf(f, "%10s %10s %8s %10s\n", "start", "size", "status", "contents");
    while (header) {
        debug_struct_info(f, header);
        header = header->next;
    }
}

void debug_block(struct block_header* b, const char* fmt, ...) {
    (void)b;
    (void)fmt;
}

void debug(const char* fmt, ...) {
    (void)fmt;
}