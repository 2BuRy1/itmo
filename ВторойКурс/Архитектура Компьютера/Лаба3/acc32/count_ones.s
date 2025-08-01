.data

input_addr: .word 0x80                    ;input address
output_addr: .word 0x84                   ;output adress
number: .word 0                           ;var for storing input data
mask: .word 0x1                           ;the mask for defining whether the smallest bit is one or zero
counter: .word 0x0                        ;ones counter
amount: .word 0x1                         ;value for shift and increment
maskForNeg: .word 0x7fffffff              ;mask for negative numbers to do the correct shift without saving the sign
.text


_start:
    load_ind input_addr                   ;load the input value
    store number                          ;save it to the number var

loop:                                     ;while number != 0
    load number                           ;load number value
    ble ifneg                             ;if number is negative, go to ifneg label
    beqz end                              ;if numer is equal to zero, "break"
    and mask                              ;mask for number to cut the smallest bit
    bnez incr                             ;if not zero, go to increment the counter
    load number                           ;if zero load the number
    shiftr amount                         ;do right shift to remove previous smallest bit
    store number                          ;save the number
    jmp loop
incr:                                     ;if smalles bit was "1"
    load counter                          ;load counter value
    add amount                            ;increment counter
    store counter                         ;save counter
    load number                           ;load number value
    shiftr amount                         ;do right shift to remove previous smallest bit
    store number                          ;save number
    jmp loop                              ;go to while

ifneg:                                    ;if number is negative
    load number                           ;load number
    and maskForNeg                        ;remove the sign bit
    store number                          ;save number value
    load counter                          ;load counter
    add amount                            ;increment counter for the sign value
    store counter                         ;save counter
    jmp loop                              ;go to while

end:                                      ;end of the program
    load counter                          ;load counter
    store_ind output_addr                 ;print (counter)
    halt
