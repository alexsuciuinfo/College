element(X,[H|_]) :- X==H,!.
element(X,[_|T]) :- element(X,T).
 
elimina([],[]).
elimina([H|T],R) :- element(H,T),elimina(T,R).
elimina([H|T],[H|R]) :- elimina(T,R).