section .data 


%define EXIT 60
%define STDOUT 1
%define WRITE 1
%define SUCCESS 1


buf: db 0                      ; Буфер для хранения одного символа

section .text
 
; Завершает текущий процесс с кодом возврата
exit: 
    mov rax, EXIT               ; Код системного вызова для exit
    syscall                    

; Принимает указатель на нуль-терминированную строку, возвращает её длину
string_length:
    xor rax, rax              ; Обнуляем rax (длина строки)
    xor rcx, rcx              ; Обнуляем rcx (индекс)
.loop:
    cmp byte [rdi], 0          ; Проверяем, достигли ли конца строки
    jz .done                   ; Если да, переходим к завершению
    inc rax                    ; Увеличиваем длину строки
    inc rdi                    ; Переходим к следующему символу
    jmp .loop                  ; Повторяем цикл
.done: 
    ret                        
   
; Принимает указатель на нуль-терминированную строку и выводит её в stdout
print_string:
    push rdi                   ; Сохраняем указатель на строку
    call string_length         ; Получаем длину строки
    pop rsi                    ; Восстанавливаем указатель
    mov rdx, rax               
    mov rax, WRITE                 
    mov rdi, STDOUT             
    syscall                    
    ret                        

; Выводит один символ в stdout
print_char:
    mov [buf], rdi             ; Сохраняем символ в буфер
    mov rax, WRITE                 
    mov rsi, buf               
    mov rdi, STDOUT                
    mov rdx, 1                 
    syscall                    
    ret                       

; Выводит символ новой строки (0xA)
print_newline:
    xor rax, rax               
    mov rdi, '\n'             
    jmp print_char          
            

; Выводит беззнаковое 8-байтовое число в десятичном формате 
print_uint:
    mov rax, rdi               ; Загружаем число в rax
    mov rdi, rsp               ; Указываем стек для хранения остатков
    sub rsp, 24                ; Выделяем место на стеке
    mov byte[rdi - 1], 0       ; Завершаем строку нулем
    mov r8, 10                 ; Устанавливаем делитель 
    dec rdi                    ; Уменьшаем указатель для записи
.loop:
    xor rdx, rdx               ; Обнуляем rdx перед делением
    div r8                     ; Делим rax на 10, результат в rax, остаток в rdx
    add dl, '0'                ; Преобразуем остаток в ASCII
    dec rdi                    ; Переходим к предыдущему байту
    mov [rdi], dl              ; Записываем символ в буфер
    test rax, rax              ; Проверяем, достигли ли нуля
    jnz .loop                  ; Если нет, продолжаем
    call print_string          ; Выводим строку
    add rsp, 24                ; Освобождаем место на стеке
    ret                        

; Выводит знаковое 8-байтовое число в десятичном формате 
print_int:
    xor rax, rax               
    cmp rdi, 0                 ; Сравниваем с нулем
    jge print_uint             ; Если больше или равно нулю, вызываем print_uint
    neg rdi                    ; Меняем знак числа
    push rdi                   ; Сохраняем число
    mov rdi, '-'               ; Устанавливаем символ '-'
    call print_char            ; Выводим знак
    pop rdi                    ; Восстанавливаем число
    jmp print_uint             
    ret                        

; Сравнивает две нуль-терминированные строки, возвращает 1 если равны, 0 иначе
string_equals:

xor rax, rax               
.loop:
    mov al, byte[rdi]         ; Загружаем символ из первой строки
    cmp al, byte[rsi]         ; Сравниваем символы
    jne .not_equal             ; Если не равны, то выход
    test al, al                ; Проверяем на конец строки
    jz .equal                  ; Если конец строки, строки равны
    inc rdi                    ; Переходим к следующему символу первой строки
    inc rsi                    ; Переходим к следующему символу второй строки
    jmp .loop                  ; Повторяем цикл
.not_equal:    
    ret                        ; Возвращаем 0
.equal:
    mov rax, SUCCESS                 ; Возвращаем 1
    ret

; Читает один символ из stdin и возвращает его
; Возвращает 0 если достигнут конец потока
read_char:
    xor rax, rax               
    push rax                   ; Сохраняем rax на стеке
    mov rdx, 1                 
    xor rdi, rdi              
    xor rax, rax               
    mov rsi, rsp               ; Указываем место для сохранения символа
    syscall                    
    pop rax                    ; Восстанавливаем rax
    ret                        

; Принимает: адрес начала буфера в rdi, размер буфера в rsi
; Возвращает: адрес буфера в rax, длину слова в rdx
; При неудаче возвращает 0 в rax
read_word:
    push r12                   ; Сохраняем r12 на стеке
    push r13                   ; Сохраняем r13 на стеке
    push r14                   ; Сохраняем r14 на стеке
    mov r12, rdi               ; Сохраняем адрес буфера
    mov r13, rsi               ; Сохраняем размер буфера
    xor r14, r14               ; Обнуляем r14 (длина слова)

    .skip_symbol:
        call read_char         ; Читаем символ
        cmp rax, 0x20         ; Проверяем пробел
        je .skip_symbol        ; Пропускаем пробелы
        cmp rax, 0x9          ; Проверяем табуляцию
        je .skip_symbol        ; Пропускаем табуляцию
        cmp rax, 0xA          ; Проверяем новую строку
        je .skip_symbol        ; Пропускаем новую строку
        test al, al            ; Проверяем конец потока
        jz .fail               ; Если конец, то выходим

    .loop:
        mov byte[r12 + r14], al ; Сохраняем символ в буфер
        inc r14                 ; Увеличиваем длину слова
        dec r13                 ; Уменьшаем оставшееся место в буфере
        jz .fail                ; Если нет места, переход к .fail
        call read_char          ; Читаем следующий символ
  
    .check_whiteSpace:
        cmp rax, 0x20           ; Проверяем пробел
        je .success             ; Если пробел, переход к .success
        cmp rax, 0x9            ; Проверяем табуляцию
        je .success             ; Если табуляция, переход к .success
        cmp rax, 0xA            ; Проверяем новую строку
        je .success             ; Если новая строка, переход к .success
        test al, al             ; Проверяем конец потока
        jz .success             ; Если конец, переход к .success
        jmp .loop               ; Продолжаем цикл
  
.success:
    mov rdx, r14               ; Длина слова в rdx
    mov rax, r12               ; Возвращаем адрес буфера в rax
    mov byte[r14+r12], 0       ; Добавляем нуль-терминал
	jmp .end               ; Заканчиваем
       
.fail:
	
	xor rax, rax           ; Неудача
        xor rdx, rdx
	

        
        

.end:
    pop r14                    ; Восстанавливаем r14 
    pop r13		       ; Восстанавливаем r13
    pop r12	               ; Восстанавливаем r12
    ret
	
	      
      
    
  	        
       






; Принимает указатель на строку, пытается
; прочитать из её начала беззнаковое число.
; Возвращает в rax: число, rdx : его длину в символах
; rdx = 0 если число прочитать не удалось
parse_uint:
        xor rsi, rsi                ; Обнуляем rsi        
	xor rax, rax                ; Обнуляем rax
	mov r9, 10                  ; r9-делитель
	xor r8, r8                  ; r8-счетчик
	.loop:
	mov sil, byte[rdi + r8]     ; Помещаем цифру в буфер

	sub  sil, '0'		    ; Парсим в ASCII и проверяем, чтобы символ был цифрой!!
	jl return                   ; иначе в return
	cmp sil, 9                  
	jg return 
	mul r9                      ; Умножаем на 10 для чисел с несколькими цифрами
	add rax, rsi                
	inc r8                      ; Увеличиваем длину числа
	jmp .loop	
return: 
	mov rdx, r8                 ; В rax число, в rdx его длина
	ret




; Принимает указатель на строку, пытается
; прочитать из её начала знаковое число.
; Если есть знак, пробелы между ним и числом не разрешены.
; Возвращает в rax: число, rdx : его длину в символах (включая знак, если он был) 
; rdx = 0 если число прочитать не удалось
    

parse_int:

   push rdi                  ; Сохраняем rdi
   xor rax, rax              ; Обнуляем rax
   
   cmp byte[rdi], `-`        ; Проверка на отрицательность числа
   je .negative_number       ; В .negative_number, если да
   
   call parse_uint           ; Иначе парсим, как беззнаковое          
   jmp .normal_end           ; Выходим
   
 
 .negative_number: 
 	inc rdi              ; Увеличиваем указатель для проверки на пробел между знаком и числом
 	
 	mov rax, [rdi]       ; Символ после знака в rax
 	
 	test rax, rax        ; Если пробел, то выходим
 	je .bad_end          
 	
 	
 	call parse_uint      ; Парсим число
 	
 	neg rax              ; В дополнительный код
 	inc rdx              ; Считаем длинну
 	jmp .normal_end 
 	


.bad_end:
	pop rdi              ; Восстанавливаем rdi
	xor rdx, rdx         ; Записываем 0 в rdx
	ret 


.normal_end:
	pop rdi              ; Восстанавливаем rdi 
	ret	 	     
 	
 	

   

; Принимает указатель на строку, указатель на буфер и длину буфера
; Копирует строку в буфер
; Возвращает длину строки если она умещается в буфер, иначе 0
string_copy:
    xor rax, rax                                                   ; Обнуляем rax
    xor rcx, rcx 						   ; Обнуляем rcx
.write_loop:
	cmp rax, rdx 						   ; Проверка на то, есть ли место в буфере 
	jz .bad_end                                                ; Если нет, то выходим
	
	mov rcx, [rdi + rax]                                       ; Помещаем символ в регистр для копирования
	
	mov [rsi + rax], rcx                                       
	
	test rcx, rcx                                       
	jz .end 						   ; Eсли встретили 0, то окончание, иначе продолжаем
	
	inc rax                                                    ; Увеличивыем счетчик
	
	jmp .write_loop
	
	
.bad_end:
	xor rax, rax                                               ; 0 в rax
	ret 

.end: 
	ret		
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
