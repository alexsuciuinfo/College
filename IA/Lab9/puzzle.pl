h([Gol|Placute],H) :- h1([Gol|Placute],[2/2,1/3,2/3,3/3,3/2,3/1,2/1,1/1,1/2],0,H1), H is H1.

h1([],[],H,N) :- N is H.
h1([Gol|Placute],[Gol1|Placute1],H,N) :- Gol == Gol1, h1(Placute,Placute1,H,N) ; H1 is H+1, h1(Placute,Placute1,H1,N).


succ([GolX/GolY|Placute],[GolX1/GolY1|Placute1],1) :- GolX > 1, Goli is GolX-1,
muta([GolX/GolY|Placute],Goli/GolY,GolX/GolY,[Gol2|Placute1]), GolX1 is Goli, GolY1 is GolY.
succ([GolX/GolY|Placute],[GolX1/GolY1|Placute1],1) :- GolX < 3, Goli is GolX+1, 
muta([GolX/GolY|Placute],Goli/GolY,GolX/GolY,[Gol2|Placute1]), GolX1 is Goli, GolY1 is GolY.
succ([GolX/GolY|Placute],[GolX1/GolY1|Placute1],1) :- GolY > 1, Goli is GolY-1,
muta([GolX/GolY|Placute],GolX/Goli,GolX/GolY,[Gol2|Placute1]), GolX1 is GolX, GolY1 is Goli.
succ([GolX/GolY|Placute],[GolX1/GolY1|Placute1],1) :- GolY < 3, Goli is GolY+1,
muta([GolX/GolY|Placute],GolX/Goli,GolX/GolY,[Gol2|Placute1]), GolX1 is GolX, GolY1 is Goli.

muta([],_,_,[]).
muta([H|T],X,Y,[Y|R]) :- H==X, muta(T,X,Y,R), !.
muta([H|T],X,Y,[H|R]) :- muta(T,X,Y,R).

arata_solutie([]).
arata_solutie([H|T]) :- arata_solutie(T), afisare(H), write('-----'), nl.

afisare(L) :- afis(L,1/3,0) , afis(L,2/3,0), afis(L,3/3,0), nl,
afis(L,1/2,0) , afis(L,2/2,0), afis(L,3/2,0), nl,
afis(L,1/1,0) , afis(L,2/1,0), afis(L,3/1,0), nl.

afis([H|T],H,N) :- N is 0 , write('  ') ; write(N), write(' ').
afis([H|T],X,N) :- N1 is N+1, afis(T,X,N1).

bestfirst(Nod_initial,Solutie):- expandeaza([],l(Nod_initial,0/0),9999999,_,da,Solutie).

expandeaza(Drum,l(N,_),_,_, da,[N|Drum]):-scop(N).

expandeaza(Drum,l(N,F/G),Limita,Arb1,Rez,Sol) :- F =< Limita,
(bagof(M/1,(succ(N,M,L), \+ (member(M,Drum))),Succ),!,
listasucc(G,Succ,As),cea_mai_buna_f(As,F1),
expandeaza(Drum,t(N,F1/G,As),Limita,Arb1, Rez,Sol);
Rez=imposibil).

expandeaza(Drum,t(N,F/G,[A|As]),Limita,Arb1,Rez,Sol) :- F =< Limita,
cea_mai_buna_f(As,BF),min(Limita,BF,Limita1),
expandeaza([N|Drum],A,Limita1,A1,Rez1,Sol),
continua(Drum,t(N,F/G,[A1|As]),Limita,Arb1,Rez1,Rez,Sol).

expandeaza(_,t(_,_,[]),_,_,imposibil,_) :- !.

expandeaza(_,Arb,Limita,Arb,nu,_) :- f(Arb,F), F > Limita.

continua(_,_,_,_,da,da,Sol).

continua(P,t(N,F/G,[A1|As]),Limita,Arb1,nu,Rez, Sol):-
insereaza(A1,As,NAs),cea_mai_buna_f(NAs,F1),
expandeaza(P,t(N,F1/G,NAs),Limita,Arb1,Rez,Sol).

continua(P,t(N,F/G,[_|As]),Limita,Arb1,imposibil,Rez,Sol):- cea_mai_buna_f(As,F1),
expandeaza(P,t(N,F1/G,As),Limita,Arb1,Rez,Sol).

listasucc(_,[],[]).
listasucc(G0,[N/C|NCs],Ts) :- G is G0+C,h(N,H),F is G+H,listasucc(G0,NCs,Ts1),
insereaza(l(N,F/G),Ts1,Ts).

insereaza(A,As,[A|As]) :- f(A,F),cea_mai_buna_f(As,F1),F =< F1,!.
insereaza(A,[A1|As],[A1|As1]):-insereaza(A,As,As1).

min(X,Y,X) :- X=<Y,!.
min(_,Y,Y).

f(l(_,F/_),F). 
f(t(_,F/_,_),F).

cea_mai_buna_f([A|_],F) :- f(A,F).
cea_mai_buna_f([],999999). 

scop([2/2,1/3,2/3,3/3,3/2,3/1,2/1,1/1,1/2]).
initial([2/1,1/2,1/3,3/3,3/2,3/1,2/2,1/1,2/3]).

puzzle:-tell('D:\\amuz\\Facultate\\IA\\puzzle.txt'),initial(Poz),bestfirst(Poz,Sol),arata_solutie(Sol),told.
alex(Sol) :- initial(Poz), bestfirst(Poz,Sol), arata_solutie(Sol).