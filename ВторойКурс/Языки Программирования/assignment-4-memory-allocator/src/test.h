#ifndef TEST_H
#define TEST_H


void test_heap_init_and_term();
void test_malloc_and_free();
void test_split_block();
void test_merge_blocks();
void test_alloc_small_block();
void test_alloc_large_block();
void test_reallocate_after_free();
void test_continuous_allocation();
void test_invalid_free();

void do_all_tests();

#endif // TEST_H