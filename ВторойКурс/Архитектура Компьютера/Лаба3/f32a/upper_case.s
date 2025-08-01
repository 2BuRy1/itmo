.data
buffer: .byte '________________________________'        \ main buffer
aligment_for_buf: .byte '___'                           \ alligment for buffer, because it can errase counter
mask: .byte 0,'___'                                     \ mask for symbols
input_addr: .word 0x80                                  \ input
difference: .word 0x20                                  \ constant to conver lower cased symbol to upper cased
symbol_counter: .word 0                                 \ word length
buffer_size: .word 32                                   \ buffer max_length
small_a: .byte 'a'                                      \ 'a'
small_z: .byte 'z'                                      \ 'z'
output_addr: .word 0x84                                 \ output
aligment: .word '..........................'            \ alligment

.text

_start:
lit buffer lit 1
+ a!                                                    \ store buffer addr from the data stack
@p input_addr b!                                        \ store input addres to the B register
while:
    @p symbol_counter                                   \ get word length
    @p buffer_size inv lit 1 +                          \ compare it with the buffer size
    +
    if error                                           \ if word length > buffer size, quit with error
    @b                                                  \ get letter
    check_is_upper                                      \ check whether letter is in upper case or not
    lit 255 and                                         \ cut all except the lowest one
    dup                                                 \ duplicate letter because of the 'if' statement
    lit -10 + if final_output                           \ if letter == '\n' go to final_output label
    @p mask +                                           \ add mask, because machine word is 32 bits, but we use only 8
    !+                                                  \ save the letter to the buffer
    @p symbol_counter                                   \ increment letter count
    lit 1 +
    !p symbol_counter
    while ;



error:
    lit 0xCCCC_CCCC                                     \ if word length > buffer, exit with error
    @p output_addr b!
    !b
    halt


final_output:
    load_length                                         \ call load_length
    lit buffer a!                                       \ store word ptr to the A register
    @+ lit 255 and                                      \ get word length and cut all bits except the lowest one
    @p output_addr b!                                   \ store output address to the B register
    final_loop:
        @+ lit 255 and                                  \ get symbol and cut all bits except the lowest one
        !b                                              \ print symbol
        lit -1 +                                        \ decrement counter (word length)
        dup                                             \ duplicate it
        if end                                          \ if length == 0, exit
        final_loop ;                                    \ while length != 0



check_is_upper:
    dup                                                 \ duplicate the symbol, because we can lose it
    @p small_z lit 255 and                              \ if symbol is higher than z in ASCII table, then it's not lower case
    inv lit 1 + +
    -if leave_symbol                                    \ret
    dup                                                 \ duplicate for saving it
    @p small_a lit 255 and                              \ if symbol is higher than a in ASCII table, then it's in lower case
    inv lit 1 + +
    -if do_uppercase                                    \ upper case the symbol
    ;


leave_symbol:
    ;                                                   \ ret


do_uppercase:
    @p difference                                       \ lower cased symbol - 32 == upper cased symbol
    inv lit 1 + +
    ;                                                   \ ret


load_length:
    drop                                                \ pop '\n'
    @p symbol_counter                                   \ get counter
    lit buffer                                          \ store buffer ptr to the A register
    a! @                                                \ get first element of the buffer
    lit 0x5f inv                                        \ first element is 0x5f
    lit 1 + +                                           \ nullify value
    @p symbol_counter +                                 \ length + first 3 letters
    !p buffer                                           \ store to the buffer
    ;


end:
    halt
