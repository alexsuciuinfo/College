rezolva_b(NodInitial,Solutie,L):-breadthfirst([[NodInitial]],Solutie,L).

breadthfirst([[Nod|Drum]|_],[Nod|Drum],L):-scop(Nod).
breadthfirst([Drum|Drumuri],Solutie,L):-extinde(Drum,DrumNoi,L),
concat(Drumuri,DrumNoi,Drumuri1),
breadthfirst(Drumuri1,Solutie,L).

extinde([Nod|Drum],DrumNoi,L):-bagof([NodNou,Nod|Drum],
(sol(Nod,NodNou,L),
\+ (member(NodNou,[Nod|Drum]))), DrumNoi),!.
extinde(_,[],L).


initial(stare(0,120,120,0,0,60,120)).

scop(stare(1,120,120,0,0,_,120)).

ok(NM,NC,NM1,NC1) :- NM >= 0, NC >=0, NM1 >=0, NC1 >=0,
(NM >= NC ; NM is 0), (NM1 >= NC1 ; NM1 is 0).

ultim([X],X).
ultim([H|T],X) :- ultim(T,X).

sol(stare(B,NMB,NCB,NMis,NCan,M,N),stare(B1,NMB1,NCB1,NMis1,NCan1,M,N),List) :-
member(Y,List), ultim(Y,X), member(Misionar,Y), Canibal is X - Misionar,
(Misionar >= Canibal ; Misionar is 0),
B1 is 1 - B, NMB1 is NMis + Misionar, NCB1 is NCan + Canibal,
NMis1 is NMB - Misionar, NCan1 is NCB - Canibal,
ok(NMB1,NCB1,NMis1,NCan1).

rezolva(Nod,Solutie,L):-depthfirst([],Nod,Solutie,L).
depthfirst(Drum, Nod,[Nod|Drum],L):-  scop(Nod).
depthfirst(Drum,Nod,Solution,L):-sol(Nod,Nod1,L),\+ (member(Nod1,Drum)), 
depthfirst([Nod|Drum],Nod1,Solution,L).

canibali :- tell('D:\\amuz\\Facultate\\canibali.txt'),M is 60, N is 120, initial(S), generatelist(M,[],L), rezolva_b(S,Solutie,L),
afisare(Solutie), told.

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

