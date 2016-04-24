inlocuire([],_,_,[]).
inlocuire([H|T],X,Y,[Y|R]) :- H==X, inlocuire(T,X,Y,R), !.
inlocuire([H|T],X,Y,[H|R]) :- inlocuire(T,X,Y,R).

inlocuire2([],_,_,[]).
inlocuire2([H|T],X,Y,[Y|T]) :- H==X.
inlocuire2([H|T],X,Y,[H|R]) :- inlocuire2(T,X,Y,R).
