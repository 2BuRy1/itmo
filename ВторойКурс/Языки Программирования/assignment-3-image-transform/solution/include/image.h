
#ifndef IMAGE_H
#define IMAGE_H
#include <stdint.h>

struct image {
    uint64_t width, height;
    struct pixel* data;
};

struct pixel {
    uint8_t r;
    uint8_t g;
    uint8_t b;
};



#endif //IMAGE_H
