.data

input_addr: .word 0x80
output_addr: .word 0x84

.text


.org 0x90

_start:
    jal     a0, initialize_registers
    xor     a0, a0, a0
    jal     a0, sum_words
    jal     a0, output

end:
    halt




initialize_registers:
    addi    sp, sp, -4                      ; stack_pointer - 4
    sw      a0, 0(sp)                       ; save return adress to into a stack

    lui     t0, %hi(input_addr)             ; higher byte to higher byte
    addi    t0, t0, %lo(input_addr)         ; lower byte to lower byte
    lw      t0, 0(t0)                       ; load the input_addr itself

    lui     s1, %hi(output_addr)            ; load output_addr value
    addi    s1, s1, %lo(output_addr)        ;
    lw      s1, 0(s1)                       ; load address from output_addr value

    lui     t4, 0x80000                     ; sign mask
    addi    s3, s3, -1                      ; FFFf...

    j ret                                   ; goto sum_words

output:
    addi    sp, sp, -4                      ; stack_pointer - 4
    sw      a0, 0(sp)                       ; save return adress to into a stack

    sw  a2, 0(s1)                           ; return high word
    sw  a1, 0(s1)                           ; return low word

    j ret                                   ; exit the program




sum:
    addi  sp, sp, -4                        ; stack_pointer - 4
    sw   a0, 0(sp)                          ; save return adress to into a stack
    add  a1, a1, t2                         ; a1 = sum (low word of sum)
    and  t2, t2, t4                         ; if value is negative, then there was an overflow
    beqz t2, ret
    addi  a2, a2, -1
    j ret

sum_words:
    addi    sp, sp, -4                      ; stack_pointer - 4
    sw      a0, 0(sp)                       ; save return address to the stack
loop:

    lw       t2, 0(t0)                      ; load next operand (t2 = operand)

    beqz     t2,  ret                       ; if operand == 0, exit

    sub     s4, s3, a1
    bleu    t2, s4 , call_sum               ; if no overflow, then there was a carry flag
    addi a2, a2, 1

call_sum:
    jal a0, sum
    j loop


ret:
  lw        a0, 0(sp)                       ; load return address from stack
  addi      sp, sp, 4                       ; free stack memory
  jr        a0                              ; ret
