h(stare(B,NM0,NC0,NM1,NC1,M,N),Rez) :-
Rez is (2 * ((NM0 + NC0 - 2) div (M-1)) + 1) , (NM0 + NC0) =\= 0, B is 0;
Rez is (2 * ((NM1 + NC1 - 1) div (M-1)) + 2) , (NM1 + NC1) =\= 0, B is 1;
Rez is 0. 

initial(stare(0,120,120,0,0,60,120)).
scop(stare(1,120,120,0,0,_,120)).

ok(NM,NC,NM1,NC1) :- NM >= 0, NC >=0, NM1 >=0, NC1 >=0,
(NM >= NC ; NM is 0), (NM1 >= NC1 ; NM1 is 0).

ultim([X],X).
ultim([H|T],X) :- ultim(T,X).

succ(stare(B,NMB,NCB,NMis,NCan,M,N),stare(B1,NMB1,NCB1,NMis1,NCan1,M,N),List) :-
member(Y,List), ultim(Y,X), member(Misionar,Y), Canibal is X - Misionar,
(Misionar >= Canibal ; Misionar is 0),
B1 is 1 - B, NMB1 is NMis + Misionar, NCB1 is NCan + Canibal,
NMis1 is NMB - Misionar, NCan1 is NCB - Canibal,
ok(NMB1,NCB1,NMis1,NCan1).

afisare([],_).
afisare([stare(B,NM,NC,NM1,NC1,M,N)|L]) :- afisare(L),
(B is 0, format('Pe malul stang sunt ~d misionari si ~d canibali \n',[NM,NC]),
format('Pe malul drept sunt ~d misionari si ~d canibali \n',[NM1,NC1]), nl) ;
(B is 1, format('Pe malul stang sunt ~d misionari si ~d canibali \n',[NM1,NC1]),
format('Pe malul drept sunt ~d misionari si ~d canibali \n',[NM,NC]), nl).


generatelist(0,L,L1) :- inversare(L,[],L1).
generatelist(N,L,L1) :- generate(N,[],L2), N1 is N-1, generatelist(N1,[L2|L],L1).

inversare([H|T],A,R) :- inversare(T,[H|A],R). 
inversare([],A,A).

generate(-1,L,L1) :- L1 = L.
generate(N,L,L1) :- N1 is N-1, generate(N1,[N|L],L1).

concat([],L,L).
concat([H1|T1],L,[H1|T2]) :- concat(T1,L,T2).

bestfirst(Nod_initial,Solutie,L):- expandeaza([],l(Nod_initial,0/0),9999999,_,da,Solutie,L).

expandeaza(Drum,l(N,_),_,_, da,[N|Drum],L):-scop(N).

expandeaza(Drum,l(N,F/G),Limita,Arb1,Rez,Sol,L) :- F =< Limita,
(bagof(M/1,(succ(N,M,L), \+ (member(M,Drum))),Succ),!,
listasucc(G,Succ,As),cea_mai_buna_f(As,F1),
expandeaza(Drum,t(N,F1/G,As),Limita,Arb1, Rez,Sol,L);
Rez=imposibil).

expandeaza(Drum,t(N,F/G,[A|As]),Limita,Arb1,Rez,Sol,L) :- F =< Limita,
cea_mai_buna_f(As,BF),min(Limita,BF,Limita1),
expandeaza([N|Drum],A,Limita1,A1,Rez1,Sol,L),
continua(Drum,t(N,F/G,[A1|As]),Limita,Arb1,Rez1,Rez,Sol,L).

expandeaza(_,t(_,_,[]),_,_,imposibil,_,L) :- !.

expandeaza(_,Arb,Limita,Arb,nu,_,L) :- f(Arb,F), F > Limita.

continua(_,_,_,_,da,da,Sol,L).

continua(P,t(N,F/G,[A1|As]),Limita,Arb1,nu,Rez, Sol,L):-
insereaza(A1,As,NAs),cea_mai_buna_f(NAs,F1),
expandeaza(P,t(N,F1/G,NAs),Limita,Arb1,Rez,Sol,L).

continua(P,t(N,F/G,[_|As]),Limita,Arb1,imposibil,Rez,Sol,L):- cea_mai_buna_f(As,F1),
expandeaza(P,t(N,F1/G,As),Limita,Arb1,Rez,Sol,L).

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


canibali :- tell('D:\\amuz\\Facultate\\IA\\canibali.txt'),M is 60, N is 120, initial(S), generatelist(M,[],L), bestfirst(S,Solutie,L),
afisare(Solutie), told.

