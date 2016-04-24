concat([],L,L).
concat([H1|T1],L,[H1|T2]) :- concat(T1,L,T2).

concat1([],L,[L]).
concat1([H1|T1],L,[H1|T2]) :- concat1(T1,L,T2).

tab(0).
tab(X) :- X1 is X-1, write(' '), tab(X1).
tabul(X) :- write(X) , tab(X), write(X).

membru(X,[X|T]) :- X \= [].
membru(X,[H|T]) :- membru(X,T).

det_poz_el(Lista_stive,N,X) :- det_poz_el1(Lista_stive,0,N,X).
det_poz_el1([X|T],N1,N,X) :- N is N1.
det_poz_el1([H|T],N1,N,X) :- N2 is N1+1, det_poz_el1(T,N2,N,X).

sterg_la_n([[H|T]|T1],[T|T1],0).
sterg_la_n([H|T],[H|T1],N) :- N > 0, ! , N1 is N-1, sterg_la_n(T,T1,N1).

adaug_la_n(X,[[]|T1],[[X]|T1],0).
adaug_la_n(X,[[H|T]|T1],[[X,H|T]|T1],0).
adaug_la_n(X,[H|T],[H|T1],N) :- N > 0, !, N1 is N-1, adaug_la_n(X,T,T1,N1).

sol(Lista_stive,Lista_stive_rez):-membru(X,Lista_stive), X = [Varf|_],
det_poz_el(Lista_stive,N,X),
sterg_la_n(Lista_stive,Lista_stive_inter,N),
member(Y,Lista_stive),det_poz_el(Lista_stive,N1,Y),N1\=N,
adaug_la_n(Varf,Lista_stive_inter,Lista_stive_rez,N1).

initial([[d],[a,b],[c]]).
scop([[],[a,b,c,d],[]]).

rezolva(Nod,Solutie):-depthfirst([],Nod,Solutie).
depthfirst(Drum, Nod,[Nod|Drum]):-scop(Nod).
depthfirst(Drum,Nod,Solution):-sol(Nod,Nod1),\+ (membru(Nod1,Drum)),
depthfirst([Nod|Drum],Nod1,Solution).


pb_df :- tell('D:\\amuz\\Facultate\\bloc_mut_ies_df.txt'), initial(S), rezolva(S,Solutie),afisare(Solutie), told.
pb_bf :- tell('D:\\amuz\\Facultate\\bloc_mut_ies_bf.txt'), initial(S), rezolva_b(S,Solutie),afisare(Solutie), told.
alex(Solutie) :- initial(S), rezolva(S,Solutie).

afisare([]).
afisare([H|T]) :- afisare(T), maxim(H,Max), afis(H,Max).

afis(L,0) :-  write('============='), nl.
afis(H,Max) :- L = [], afis1(H,Max,4,L).

afis1([],Max,P,L) :- nl, Max1 is Max-1, afis(L,Max1).
afis1([[]|T1],Max,P,L) :- tab(P), concat1(L,[],L1), afis1(T1,Max,P,L1).
afis1([[H|T]|T1],Max,P,L) :- (nr_nivel([H|T],0,N), N =:= Max, write(H), tab(P), concat1(L,T,L1), afis1(T1,Max,P,L1)) ;
(tab(P), concat1(L,[H|T],L1), afis1(T1,Max,P,L1)).  

maxim([],0).
maxim([H|T],Max) :- maxim(T,Max1), nr_nivel(H,0,N), Max is max(N,Max1).

nr_nivel([],N,N1) :- N1 is N.
nr_nivel([X|T],N,N2) :- N1 is N+1, nr_nivel(T,N1,N2).

rezolva_b(NodInitial,Solutie):-breadthfirst([[NodInitial]],Solutie).
breadthfirst([[Nod|Drum]|_],[Nod|Drum]):-scop(Nod).
breadthfirst([Drum|Drumuri],Solutie):-extinde(Drum,DrumNoi),
concat(Drumuri,DrumNoi,Drumuri1),
breadthfirst(Drumuri1,Solutie).
extinde([Nod|Drum],DrumNoi):-bagof([NodNou,Nod|Drum],
(sol(Nod,NodNou),
\+ (membru(NodNou,[Nod|Drum]))), DrumNoi),!.
extinde(_,[]).
