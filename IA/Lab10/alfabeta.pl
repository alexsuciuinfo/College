alphabeta(Poz,Alpha,Beta,PozBuna,Val) :-
	mutari(Poz,ListaPoz), !,
	limitarebuna(ListaPoz,Alpha,Beta,PozBuna,Val);
	staticval(Poz,Alpha).

limitarebuna([Poz|ListaPoz],Alpha,Beta,PozBuna,ValBuna) :-
	alfabeta(Poz,Alpha,Beta,_,Val),
	destuldebun(ListaPoz,Alpha,Beta,Poz,Val,PozBuna,ValBuna).
	
destuldebun([],_,_,Poz,Val,Poz,Val) :- !.

destuldebun(_,Alpha,Beta,Poz,Val,Poz,Val) :-
	mutare_min(Poz), Beta < Val;
	mutare_max(Poz), Alpha > Val.
	
destuldebun([Poz|ListaPoz],Alpha,Beta,Poz,Val,PozBuna,ValBuna) :-
	limitenoi(Alpha,Beta,Poz,Val,AlphaNou,BetaNou),
	limitarebuna(Poz,AlphaNou,BetaNou,Poz1,Val1),
	maibine(Poz,Val,Poz1,Val1,PozBuna,ValBuna).
	
limitenoi(Alpha,Beta,Poz,Val,Val,Beta) :-
	mutare_min(Poz), Val > Alpha.

limitenoi(Alpha,Beta,Poz,Val,Alpha,Val) :-
	mutare_max(Poz), Val < Beta.

limitenoi(Alpha,Beta,_,_,Alpha,Beta) :- !.