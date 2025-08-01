

#ifndef IMAGE_LIFE_H
#define IMAGE_LIFE_H

#include "image.h"


struct image create(uint64_t width, uint64_t height);

void free_img(struct image* image);


#endif //IMAGE_LIFE_H
