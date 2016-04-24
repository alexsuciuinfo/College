juc_opus(x,0).
juc_opus(0,x).

tablou_initial([gol,gol,gol,gol,gol,gol,gol,gol,gol]).

tablou_final([C,C,C|_],C):- C\==gol,!.
tablou_final([_,_,_,C,C,C|_],C):- C\==gol,!. 
tablou_final([_,_,_,_,_,_,C,C,C],C):- C\==gol,!. 
tablou_final([C,_,_,C,_,_,C|_],C):- C\==gol,!.    
tablou_final([_,C,_,_,C,_,_,C|_],C):- C\==gol,!.
tablou_final([_,_,C,_,_,C,_,_,C],C):- C\==gol,!.
tablou_final([C,_,_,_,C,_,_,_,C],C):- C\==gol,!.
tablou_final([_,_,C,_,C,_,C|_],C):- C\==gol,!.
tablou_final(Tablou,gol):- \+ (member(gol,Tablou)).


mutari(Poz,ListaPoz):-bagof(Poz1,succ(Poz,Poz1),ListaPoz).

succ(st(J,T,N,M),st(J1,T1,N1,M)):-
juc_opus(J,J1), N > 0, N1 is N-1,
inlocuire(T,gol,J,T1).


inlocuire([H|T],X,Y,[Y|T]) :- H==X.
inlocuire([H|T],X,Y,[H|R]) :- inlocuire(T,X,Y,R).

staticval(st(_,Tablou,_,MAX),Val):-
tablou_final(Tablou,T),!,juc_opus(MAX,MIN),
(T==MIN, Val is -99;
T==MAX, Val is 99;
T==gol,Val is 0).

staticval(st(_,Tablou,_,MAX),Val):-
juc_opus(MAX,MIN),
nr_lin_deschise(MAX,L_D_2,Tablou),
nr_lin_deschise(MIN,L_D_1,Tablou),
Val is L_D_2-L_D_1.

nr_lin_deschise(XO,L_D,Tablou):-
linia1(Tablou,L1,XO),
linia2(Tablou,L2,XO),
linia3(Tablou,L3,XO),
coloana1(Tablou,C1,XO),
coloana2(Tablou,C2,XO),
coloana3(Tablou,C3,XO),
diagonala1(Tablou,D1,XO),
diagonala2(Tablou,D2,XO),
L_D is L1+L2+L3+C1+C2+C3+D1+D2.

deschis([X,Y,Z],XO) :- 
(XO is 0, (X == 0 ; X == gol), (Y == 0 ; Y == gol), (Z == 0 ; Z == gol));
(XO == x, (X == x ; X == gol), (Y == x ; Y == gol), (Z == x ; Z == gol)).

linia1([X,Y,Z|_],1,XO) :- deschis([X,Y,Z],XO),!.
linia1(_,0,_).

linia2([_,_,_,X,Y,Z|_],1,XO) :- deschis([X,Y,Z],XO),!.
linia2(_,0,_).

linia3([_,_,_,_,_,_,X,Y,Z],1,XO) :- deschis([X,Y,Z],XO),!.
linia3(_,0,_).

coloana1([X,_,_,Y,_,_,Z,_,_],1,XO) :- deschis([X,Y,Z],XO),!.
coloana1(_,0,_).

coloana2([_,X,_,_,Y,_,_,Z,_],1,XO) :- deschis([X,Y,Z],XO),!.
coloana2(_,0,_).

coloana3([_,_,X,_,_,Y,_,_,Z],1,XO) :- deschis([X,Y,Z],XO),!.
coloana3(_,0,_).

diagnala1([X,_,_,_,Y,_,_,_,Z],1,XO) :- deschis([X,Y,Z],XO),!.
diagonala1(_,0,_).

diagonala2([_,_,X,_,Y,_,Z,_,_],1,XO) :- deschis([X,Y,Z],XO),!.
diagonala2(_,0,_).

mutare_max(st(MAX,_,_,MAX)).
mutare_min(st(MIN,_,_,MAX)):-juc_opus(MAX,MIN).

x_si_o_alfabeta:-
initializari(MAX,N),
tablou_initial(Tablou),
afis_tablou(Tablou),
joc_alfa_beta(st(x,Tablou,N,MAX)).

jucator_MAX(M):-
write('Incepe jocul ... '),nl,
repeat,
write('Vrei sa fii cu X ? (da/nu)'),nl,
read(T),
(T=da,M is 0 ;T=nu, M = x),!.

initializari(M,N):-jucator_MAX(M),
nl,repeat,
write('Adancimea: '),
read(N),integer(N),!,nl.

joc_alfa_beta(st(_,Tablou,_,MAX)):-
tablou_final(Tablou,J),!,anunt_castigator(J,MAX).

joc_alfa_beta(st(MIN,Tablou,N,MAX)):-
juc_opus(MAX,MIN),
muteu(Tablou,Tablou1,MIN),nl,
joc_alfa_beta(st(MAX,Tablou1,N,MAX)).
 
joc_alfa_beta(st(MAX,Tablou,N,MAX)):-
juc_opus(MAX,MIN),
alphabeta(st(MAX,Tablou,N,MAX),-500,500,st(MIN,Tablou1,_,MAX),_),nl,
afis_tablou(Tablou1),write('-----------'),nl,nl,
joc_alfa_beta(st(MIN,Tablou1,N,MAX)).

anunt_castigator(J,MAX):-
 J==MAX,write('Ai pierdut!'),nl;
 juc_opus(J,J_O),J_O==MAX,
 write('Ai castigat!');
 J==gol, write('Jocul s-a incheiat cu remiza '),nl.
 
muteu(Tablou,Tablou1,MIN) :-
repeat, read(Poz), insert(Tablou,MIN,Poz,Tablou1).
 
 
insert([H|T],X,1,[X|T]) :- H == gol.  
insert([H|T],X,P,[H|T1]):- P>0, !, P1 is P-1, insert(T,X,P1,T1). 

 
afis_tablou([C1,C2,C3,C4,C5,C6,C7,C8,C9]):-
 write(' 1 2 3 '),nl,nl,
 write('1 '),scrie_elem(C1),
 tab(1),scrie_elem(C2),
 tab(1),scrie_elem(C3),nl,
 write('2 '),scrie_elem(C4),
 tab(1),scrie_elem(C5),
 tab(1),scrie_elem(C6),nl,
 write('3 '),scrie_elem(C7),
 tab(1),scrie_elem(C8),
 tab(1),scrie_elem(C9),nl.

tab(0). 
tab(X) :- write(' '), X1 is X-1, tab(X1). 
 
scrie_elem(X):-
 X==gol,!,write('.');
 X==x,!,write('X');
 write('0').


alphabeta(Poz,Alpha,Beta,PozBuna,Val):- mutari(Poz,ListaPoz),!,
limitarebuna(ListaPoz,Alpha,Beta,PozBuna,Val);
staticval(Poz,Val).

limitarebuna([Poz|ListaPoz],Alpha,Beta,PozBuna,ValBuna):-
alphabeta(Poz,Alpha,Beta,_,Val),
destuldebun(ListaPoz,Alpha,Beta,Poz,Val,PozBuna,ValBuna).

destuldebun([],_,_,Poz,Val,Poz,Val):-!.
destuldebun(_,Alpha,Beta,Poz,Val,Poz,Val):-
mutare_min(Poz),Val>Beta,!;
mutare_max(Poz),Val<Alpha,!.

destuldebun(ListaPoz,Alpha,Beta,Poz,Val,PozBuna,ValBuna):-
limitenoi(Alpha,Beta,Poz,Val,AlphaNou,BetaNou),
limitarebuna(ListaPoz,AlphaNou,BetaNou,Poz1,Val1),
maibine(Poz,Val,Poz1,Val1,PozBuna,ValBuna).

limitenoi(Alpha,Beta,Poz,Val,Val,Beta):-
mutare_min(Poz),Val>Alpha,!.
limitenoi(Alpha,Beta,Poz,Val,Alpha,Val):-
mutare_max(Poz),Val<Beta,!.
limitenoi(Alpha,Beta,_,_,Alpha,Beta).

maibine(Poz0,Val0,Poz1,Val1,Poz0,Val0):-
mutare_min(Poz0),Val0>Val1,!;
mutare_max(Poz0),Val0<Val1,!.
maibine(Poz0,Val0,Poz1,Val1,Poz1,Val1).