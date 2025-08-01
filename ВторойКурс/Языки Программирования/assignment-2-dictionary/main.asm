; main.asm 


%include "dict.inc"
%include "lib.inc"
%include "words.inc"
            

section .data
    
    found_error: db "Didnt find any key", 0 
    buffer_overflow: db "Overflow!" , 0 
%define BufferSize 256
section .text
global _start



_start:
    sub rsp, BufferSize
    mov rdi, rsp
    mov rsi, BufferSize
    call read_word



    mov rdi, rsp
            
    mov rsi, last_id
    test rax, rax
    je .overflow
    
    call find_word

    test rax, rax
    jz .not_found
    

    mov rdi, rax
    
    
    call get_value 
    
    mov rdi, rax
    
    call print_string
    call print_newline
    jmp .success

.not_found:
    mov rsi, found_error
    call print_error
    
    mov rdi, 1
    jmp .exit

.overflow:
    mov rsi, buffer_overflow
    call print_error
    mov rdi, 1
    jmp .exit
    
.success: 
	xor  rdi, rdi   
    
.exit:
	add rsp, BufferSize 
	call exit
    

