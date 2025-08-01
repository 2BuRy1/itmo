main: main.o lib.o dict.o
	ld -o main main.o lib.o dict.o

main.o: main.asm
	nasm -f elf64 -o main.o main.asm

lib.o: lib.asm
	nasm -f elf64 -o lib.o lib.asm

dict.o: dict.asm
	nasm -f elf64 -o dict.o dict.asm
  
test: main
	python3 test.py
  
.PHONY: clean
clean:
	rm -f main main.o lib.o dict.o

