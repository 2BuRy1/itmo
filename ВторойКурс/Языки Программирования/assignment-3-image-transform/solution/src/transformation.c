#include "image.h"
#include "transformation.h"
#include <image_life.h>
#include <stdlib.h>


struct image rotate_clockwise(struct image const source) {
    struct image result = create(source.height, source.width);
    struct pixel* temp = malloc(sizeof(struct pixel) * source.width * source.height);

    if (result.data && temp) {
        for (uint64_t y = 0; y < source.height; ++y) {
            for (uint64_t x = 0; x < source.width; ++x) {
                temp[(source.width - 1 -x) * source.height + y] = source.data[y * source.width + x];
            }
        }
        for (uint64_t i = 0; i < source.width * source.height; ++i) {
            result.data[i] = temp[i];
        }

    }
    free(temp);
    return result;
}


struct image rotate_counterclockwise(struct image const source) {
    struct image result = create(source.height, source.width);
    struct pixel* temp = malloc(sizeof(struct pixel) * source.width * source.height);

    if (result.data && temp) {
        for (uint64_t y = 0; y < source.height; ++y) {
            for (uint64_t x = 0; x < source.width; ++x) {
                temp[(source.height - 1 - y) + source.height * x] = source.data[y * source.width + x];
            }
        }
        for (uint64_t i = 0; i < source.width * source.height; ++i) {
            result.data[i] = temp[i];
        }

    }
    free(temp);
    return result;
}


struct image mirror_vertically( struct image const source ) {
    struct image result = create(source.width, source.height);
    struct pixel* temp = malloc(sizeof(struct pixel) * source.width * source.height);
    if (result.data && temp) {
        for (size_t y = 0; y < source.height; ++y) {
            for (size_t x = 0; x < source.width; ++x) {
                temp[(source.height - 1 - y) * source.width + x] = source.data[y * source.width + x];
            }
        }
        for (uint64_t i = 0; i < source.width * source.height; ++i) {
            result.data[i] = temp[i];
        }

    }
    free(temp);
    return result;
}



struct image mirror_horizontally( struct image const source ) {
    struct image result = create(source.width, source.height);
    struct pixel* temp = malloc(sizeof(struct pixel) * source.width * source.height);
    if (result.data && temp) {
        for (size_t y = 0; y < source.height; ++y) {
            for (size_t x = 0; x < source.width; ++x) {
                temp[(source.width - 1 - x) +  source.width * y] = source.data[y * source.width + x];
            }
        }
        for(size_t i = 0; i < source.width * source.height; ++i) {
            result.data[i] = temp[i];
        }
    }
    free(temp);
    return result;
}

struct image none(struct image const source) {
    struct image result = create(source.width, source.height);
    if(result.data) {
        for(size_t i = 0; i < source.width * source.height; ++i) {
            result.data[i] = source.data[i];
        }

    }
    return result;
}

