cifre(X,R) :- X=:=0.
cifre(X,R) :- 
X>0,
R1 is X mod 10,
X1 is X div 10,
cifre(X1,R1),
(R1 =:= 0, write('(zero,0) ');
R1 =:= 1, write('(unu,1) ');
R1 =:= 2, write('(doi,2) ');
R1 =:= 3, write('(trei,3) ');
R1 =:= 4, write('(patru,4) ');
R1 =:= 5, write('(cinci,5) ');
R1 =:= 6, write('(sase,6) ');
R1 =:= 7, write('(sapte,7) ');
R1 =:= 8, write('(opt,8) ');
write('(noua,9) ')).


