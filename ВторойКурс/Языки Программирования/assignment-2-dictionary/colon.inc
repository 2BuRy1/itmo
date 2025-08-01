%define last_id 0

%macro colon 2 
%ifstr %1
%ifid %2
%2: 
	dq last_id  
	db %1,0 
%define last_id %2
%else 
%error The key value must be string
%endif 
%else 
%error The second value must be a label
%endif	
%endmacro
