#ifndef TRANSFORMATION_H
#define TRANSFORMATION_H
#include "image.h"

struct image rotate_clockwise( struct image const source );

struct image rotate_counterclockwise (struct image const source );

struct image mirror_vertically( struct image const source );

struct image mirror_horizontally( struct image const source );

struct image none( struct image const source );





#endif
