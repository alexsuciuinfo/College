divizori(X,D) :- D * D =< X, X mod D =:= 0, ! ; D * D =< X, D1 is D+1, divizori(X,D1). 
prim(X):- Y is 2, X > 1, \+divizori(X,Y). 

cmmdc(A,0,A).
cmmdc(0,A,A).
cmmdc(A,B,C) :- A >= B, D is A - B, cmmdc(D,B,C);  D is B - A, cmmdc(A,D,C). 

cmmdc1(X,0,X).
cmmdc1(X,Y,T):- R is X mod Y, cmmdc1(Y,R,T).
coprime(X,Y):- cmmdc(X,Y,T), T is 1.

fact(0,1).
fact(X,P) :-  X1 is X - 1, fact(X1,P1) , P is P1 * X.

cifre(0,_).
cifre(X,R) :- X1 is X div 10, R1 is X mod 10, cifre(X1,R1),
(R1 =:= 1, write('(unu,1)  ');
R1 =:= 2, write('(doi,2)  ');
R1 =:= 3, write('(trei,3)  ')
).

elimina(_,[],[]).
elimina(X,[X|T],T).
elimina(X,[H|T],T1) :- elimina(X,T,T1).

intersectie([],_,[]).
intersectie([H|T],L,[H|R]) :- member(H,L), intersectie(T,L,R).
intersectie([H|T],L,R) :- \+ (member(H,L)), intersectie(T,L,R).

dif([],_,[]).
dif([H|T],L,R) :- member(H,L), dif(T,L,R).
dif([H|T],L,[H|R]) :- \+ (member(H,L)), dif(T,L,R).

nrelem([],N,N1) :- N1 is N.
nrelem([H|T],N,N1) :- N2 is N+1, nrelem(T,N2,N1).

insertf([],X,X).
insertf(X,L,[X|L]) :- \+(member(X,L)).
insertf(X,L,L).

suma([],S) :- write(S).
suma([H|T],S) :- H>0, S1 is S + H, suma(T,S1) ; suma(T,S).

suma1([],0).
suma1([H|T],S) :- suma1(T,S1), H>0, S is S1+H ; suma1(T,S). 

inversare([H|T],L,R) :- inversare(T,[H|L],R).
inversare([],L,L).

minim([X],X).
minim([H|T],X) :- minim(T,X1), X is min(H,X1).

nreleme([],0).
nreleme([H|T],N) :- nreleme(T,N1), N is N1 + 1.

elempoz([H|T],1,H).
elempoz([H|T],N,R) :- N1 is N-1, elempoz(T,N1,R).

insereaza(L,1,X,[X|L]).
insereaza([H|T],N,X,[H|T1]) :- N1 is N-1, insereaza(T,N1,X,T1).

interclasare([],L,L).
interclasare(L,[],L).
interclasare([H1|T1],[H2|T2],[H1|R]) :- H1 =< H2, interclasare(T1,[H2|T2],R).
interclasare([H1|T1],[H2|T2],[H2|R]) :- H1 >= H2, interclasare([H1|T1],T2,R).

sublista(X,[],[],[]).
sublista(X,[H|T],[H|T1],T2) :- X =< H, sublista(X,T,T1,T2).
sublista(X,[H|T],T1,[H|T2]) :- X > H, sublista(X,T,T1,T2).

bubble_sort(L1,R) :- inter(L1,L2), !, bubble_sort(L2,R).
bubble_sort(L,L).
inter([A,B|T],[B,A|T]) :- A>B.
inter([A|T1],[A|T2]) :- inter(T1,T2).


adauga([H|T],Arb) :- insert1(H,Arb,Arb2), adauga(T,Arb2).
adauga([],Arb) :- write(Arb).

insert(X,nil,t(nil,X,nil)).
insert(X,t(S,R,D),R1) :- X=<R, insert(X,S,R2), R1 = t(R2,R,D).
insert(X,t(S,R,D),R1) :- X>R, insert(X,D,R2), R1 = t(S,R,R2).

insert1(X,nil,t(nil,X,nil)).
insert1(X,t(S,R,D),t(S1,R,D)) :- X=<R, insert1(X,S,S1).
insert1(X,t(S,R,D),t(S,R,D1)) :- X>R, insert1(X,D,D1).

inaltime(nil,0).
inaltime(t(S,R,D),H) :- inaltime(S,H1), inaltime(D,H2), H is 1 + max(H1,H2). 

muchii(t(nil,R,nil),[]).
muchii(t(t(S1,R1,D1), R, nil), [[R1,R] | T]) :- muchii(t(S1,R1,D1),T).
muchii(t(nil,R,t(S1,R1,D1)), [[R,R1] | T]) :- muchii(t(S1,R1,D1),T).
muchii(t(t(S1,R1,D1), R, t(S2,R2,D2)),[[R1,R],[R2,R]| T]) :- muchii(t(S1,R1,D1),T1), muchii(t(S2,R2,D2),T2), concat1(T1,T2,T).

concat1([],L,L).
concat1([H1|T1],L,[H1|T2]) :- concat1(T1,L,T2).

sterg(nil,X,nil).
sterg(t(nil,R,nil),R,nil).
sterg(t(S,R,nil),R,S).
sterg(t(nil,R,D),R,D).
sterg(t(S,R,D),R,t(S1,Max,D)) :- max_st(S,Max,S1).
sterg(t(S,R,D),X,t(S1,R,D)) :- X < R, sterg(S,X,S1).
sterg(t(S,R,D),X,t(S,R,D1)) :- sterg(D,X,D1).

max_st(t(S,R,nil),R,S).
max_st(t(S,R,D),Max,t(S,R,D1)) :- max_st()

