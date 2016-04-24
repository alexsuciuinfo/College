dame(L,L1) :- test(L,L1,1,[],[]).

test([],[],_,_,_).
test(L1,[Y|Ys],X,D1,D2) :- elimina(Y,L1,L), 
	R1 is X-Y, \+ member(R1,D1),
	R2 is X+Y, \+ member(R2,D2),
	X1 is X+1,
	test(L,Ys,X1,[R1|D1],[R2|D2]).
	
elimina(H,[H|T],T).
elimina(X,[H|T],[H|T1]):- elimina(X,T,T1).