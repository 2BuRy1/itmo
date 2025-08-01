#pragma once

#define _DEFAULT_SOURCE
#include <unistd.h>
#include <sys/mman.h>
#define _GNU_SOURCE

static inline void * _mmap(void * addr, size_t length, int prot, int flags, int fd, off_t offset);


#define mmap _mmap
#undef _DEFAULT_SOURCE
#include "../../src/mem.c"
#undef mmap

#include "../../src/util.c"

#include "test_utils.h"
