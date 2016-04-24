ida(Nod,Sol) :- 
	cale(Nod,NodScop,Sol),
	scop(NodScop),!.
	
cale(Nod,Nod,[Nod]).
cale(PrimNod,UltimNod,[UltimNod|Drum]) :-
	cale(PrimNod,PenultimNod,Drum),
	succ(PenultimNod,UltimNod),
	\+(member(UltimNod,Drum)).
	
rezolva_b(Start,Sol) :- breadthfirst([[Start]],Sol).

breadthfirst([[Nod|Drum]|_],[Nod|Drum]) :- scop(Nod).

breadthfirst([Drum|Drumuri],Sol) :-
	extinde(Drum,DrumuriNoi),
	concat(Drumuri,DrumuriNoi,Drumuri1),
	breadthfirst(Drumuri1,Sol).
	
extinde([Nod|Drum],DrumuriNoi) :-
	bagof([NodNou,Nod|Drum],(succ(Nod,NodNou), \+(member(NodNou,[Nod|Drum]))), DrumuriNoi).
extinde(_,[]).
	
concat([],L,L).
concat([H|T],L,[H|T1]) :- concat(T,L,T1). 

rezolva_d(Nod,Sol) :- depthfirst([],Nod,Sol).

depthfirst(Drum,Nod,[Nod|Drum]) :- scop(Nod).
depthfirst(Drum,Nod,Sol) :-
	succ(Nod,NodNou),
	\+(member(NodNou,Drum)),
	depthfirst([Nod|Drum],NodNou,Sol).
	
scop(f).
scop(h).

succ(a,b).
succ(a,c).
succ(b,d).
succ(b,e).
succ(c,f).
succ(c,g).
succ(d,h).
succ(e,i).