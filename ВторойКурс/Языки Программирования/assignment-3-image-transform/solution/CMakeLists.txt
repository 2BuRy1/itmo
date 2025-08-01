file(GLOB_RECURSE sources CONFIGURE_DEPENDS
    src/*.c
    src/*.h
    include/*.h
)

add_executable(image-transform ${sources}
        include/bmp.h
        include/image.h
        include/transformation.h
        src/bmp.c
        src/transformation.c
        include/image_life.h
        src/image_life.c
)
target_include_directories(image-transform PRIVATE src include)
