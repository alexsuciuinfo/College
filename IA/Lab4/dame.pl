dame(L,L1) :- perm(L,L1), test(L1,1,[],[]).
 
perm([],[]).
perm(L,[H|T]) :- elimina(H,L,L1), perm(L1,T).
 
elimina(H,[H|T],T).
elimina(X,[H|T],[H|T1]):- elimina(X,T,T1).
  
test([],_,_,_).
test([Y|Ys],X,D1,D2) :- 
	R1 is X-Y, \+ member(R1,D1), 
	R2 is X+Y, \+ member(R2,D2),
	X1 is X + 1,
	test(Ys,X1,[R1|D1],[R2|D2]).