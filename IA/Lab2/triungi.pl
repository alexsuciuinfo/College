triunghi(A,B,C,D,E,F) :- 
(A * D + C * F + E * B - E * D - F * A - C * B) =\= 0.
distanta2(A,B,C,D,R) :- R is ((C-A) * (C-A) + (D-B) * (D-B)).
patrat(A1,A2,B1,B2,C1,C2,D1,D2) :- distanta2(A1,A2,B1,B2,R1), distanta2(A1,A2,C1,C2,R2),
distanta2(A1,A2,D1,D2,R3), distanta2(B1,B2,C1,C2,R4), distanta2(B1,B2,D1,D2,R5), distanta2(C1,C2,D1,D2,R6),
((R1=:=R6,R2=:=R3,R4=:=R5,R2=:=R4,R1=\=R2);
(R2=:=R5,R1=:=R3,R4=:=R6,R1=:=R4,R1=\=R2);
(R3=:=R4,R1=:=R2,R5=:=R6,R1=:=R5,R1=\=R3)).
linie(A1,A2,B1,B2) :- A1=\=B1 ; A2=\=B2.
linie_verticala(A1,A2,B1,B2) :- A1=:=B1, A2=\=B2.
linie_orizontala(A1,A2,B1,B2) :- A1=\=B1, A2=:=B2.


