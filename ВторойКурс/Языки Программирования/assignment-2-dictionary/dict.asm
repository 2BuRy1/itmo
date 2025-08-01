; dict.asm


%include "lib.inc" 


section .text


global find_word
global get_value

find_word:
    push rbx              ; Сохраняем rbx
    xor r8, r8
    mov rbx, rsi
.loop:
    test rbx, rbx            ; Проверяем конец словаря
    je .not_found         ; Если конец, то ключ не найден

    lea rsi, [rbx + 8]    ; Берём адрес ключа
    call string_equals    ; Сравниваем ключи (rdi содержит указатель на строку)
    cmp rax, 1
    je .found             ; Если найдено, то переход

    mov rbx, [rbx]        ; Переходим к следующему элементу
    jmp .loop

.found:
    lea rax, [rbx + 8]
    pop rbx
    ret

.not_found:
    xor rax, rax          ; Возвращаем 0 (ключ не найден)
    pop rbx
    ret
    
    
get_value: 
	xor r10, 10
	push rdi 
	call string_length
	mov r10, rax 
	pop rdi 
	lea rax, [rdi + r10 + 1]    
    	ret
    
