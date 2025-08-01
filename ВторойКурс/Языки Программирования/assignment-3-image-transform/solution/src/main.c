#include "bmp.h"
#include "image_life.h"
#include "transformation.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char* argv[]) {
    if (argc != 4) {
        fprintf(stderr, "Usage: %s <source-image> <transformed-image> <transformation>\n", argv[0]);
        return 1;
    }

    const char* input_file = argv[1];
    const char* output_file = argv[2];
    const char* transformation = argv[3];

    FILE* in = fopen(input_file, "rb");
    if (!in) {
        perror("Error opening input file");
        return 2;
    }

    struct image img;
    enum read_status read_status = from_bmp(in, &img);
    fclose(in);

    if (read_status != READ_OK) {
        fprintf(stderr, "Error reading BMP file: %s\n", input_file);
        return read_status;
    }

    struct image transformed_img;
    if (strcmp(transformation, "cw90") == 0) {
        transformed_img = rotate_clockwise(img);
    } else if (strcmp(transformation, "ccw90") == 0) {
        transformed_img = rotate_counterclockwise(img);
    } else if (strcmp(transformation, "fliph") == 0) {
        transformed_img = mirror_horizontally(img);
    } else if (strcmp(transformation, "flipv") == 0) {
        transformed_img = mirror_vertically(img);
    } else if (strcmp(transformation, "none") == 0) {
        transformed_img = none(img);
    } else {
        fprintf(stderr, "Unknown transformation: %s\n", transformation);
        free(img.data);
        return 1;
    }

    free(img.data);

    FILE* out = fopen(output_file, "wb");
    if (!out) {
        perror("Error opening output file");
        free(transformed_img.data);
        return 1;
    }

    enum write_status write_status = to_bmp(out, &transformed_img);
    fclose(out);

    if (write_status != WRITE_OK) {
        fprintf(stderr, "Error writing BMP file\n");
        free(transformed_img.data);
        return 1;
    }

    free(transformed_img.data);

    return 0;
}
