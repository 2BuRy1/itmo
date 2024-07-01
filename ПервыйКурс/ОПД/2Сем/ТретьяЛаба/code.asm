ORG 0x140
Start: CLA
       LD ArrAdd   	
       ST ArrayBuf 
       	

MassiveLength: IN 7
               AND #0x40 
               BEQ MassiveLength 
               IN 6
               ST (ArrayBuf)+
              
	
SaveLenght:
	
	    ST WordLenght
	    CMP CheckLenght	
            BEQ EndProgram

Symb1: 

 LD WordLenght
     CMP CheckLenght
     BEQ EndForFirstSymbol
     DEC 
     ST WordLenght
IN 0x19
       AND #0x40 
       BEQ Symb1 
       IN 0x18
       ST BUF
      
     

 
Symb2: 
 LD WordLenght
     CMP CheckLenght
     BEQ EndForFirstSymbol
     DEC 
     ST WordLenght

IN 0x19
       AND #0x40 
       BEQ Symb2 
       IN 0x18
       SWAB 
       ADD BUF
     SaveMessage:  ST (ArrayBuf)+
     JUMP Symb1
	
    

EndProgram: 
HLT


EndForFirstSymbol: 
LD BUF 
SWAB 
ST (ArrayBuf)+
HLT



ArrAdd: WORD $RES
ArrayBuf : WORD 0
WordLenght : WORD 0 
BUF : WORD 0
CheckLenght : WORD 0 
SecondSymbol : WORD 0

ORG 0x59D 
RES : WORD 0
