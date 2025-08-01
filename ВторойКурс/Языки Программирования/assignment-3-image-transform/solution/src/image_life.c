#include "image_life.h"

#include <stdlib.h>


struct image create(uint64_t width, uint64_t height) {
    struct image createdOne;
    createdOne.width = width;
    createdOne.height = height;
    createdOne.data = malloc(sizeof(struct pixel) * width * height);
    return createdOne;
}

void free_img(struct image* image) {
    if(image->data) {
        free(image->data);
        image->data = NULL;
    }
}

