#include "bmp.h"
#include <stdlib.h>

#include "image_life.h"


enum read_status from_bmp(FILE* in, struct image* img) {
    struct bmp_header header;

    if (fread(&header, sizeof(header), 1, in) != 1) {
        return READ_INVALID_HEADER;
    }

    if (header.bfType != BMP_SIGNATURE) {
        return READ_INVALID_SIGNATURE;
    }

    if (header.biBitCount != BMP_CORRECT_BITS) {
        return READ_INVALID_BITS;
    }

    // img->width = header.biWidth;
    // img->height = header.biHeight;
    // img->data = malloc(img->width * img->height * sizeof(struct pixel));


    *img = create(header.biWidth, header.biHeight);
    size_t padding = (4 - (sizeof(struct pixel) * img->width) % 4) % 4;

    if (!img->data) {
        return ENOMEM;
    }

    for (uint64_t y = 0; y < img->height; y++) {
        if (fread(&img->data[y * img->width], sizeof(struct pixel), img->width, in) != img->width) {
            free_img(img);
            return ENOMEM;
        }

        if (fseek(in, (long)padding, SEEK_CUR) != 0) {
            free_img(img);
            return READ_INVALID_PADDING;
        }
    }

    return READ_OK;
}

enum write_status to_bmp(FILE* out, struct image* img) {
    struct bmp_header header = {
        .bfType = BMP_SIGNATURE,
        .bfOffBits = sizeof(struct bmp_header),
        .biSize = 40,
        .biWidth = img->width,
        .biHeight = img->height,
        .biPlanes = 1,
        .biBitCount = BMP_CORRECT_BITS,
        .biSizeImage = img->width * img->height * sizeof(struct pixel)
    };

    header.bfileSize = header.bfOffBits + header.biSizeImage;

    if (fwrite(&header, sizeof(header), 1, out) != 1) {
        return WRITE_ERROR;
    }

    size_t padding = (4 - (img->width * sizeof(struct pixel)) % 4) % 4;

    for (uint64_t y = 0; y < img->height; y++) {
        if (fwrite(img->data + y * img->width, sizeof(struct pixel), img->width, out) != img->width) {
            return WRITE_ERROR;
        }

        if (padding > 0) {
            fwrite("\0\0\0", 1, padding, out);
        }
    }

    return WRITE_OK;
}




