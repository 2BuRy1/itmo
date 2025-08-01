.data
alligment: .word '..........................\n'
output_addr: .word 0x84
counter: .word 0
.text

_start:
    lit alligment a!
    while:
        @+
        dup
        lit -10 +
        if end
        @p counter
        lit 1 +
        !p counter
        while ;
end:
    @p counter
    @p output_addr b!
    !b
    halt
