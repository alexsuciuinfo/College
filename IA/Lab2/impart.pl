imparte([],X,[],[]).
imparte([H|T],X,[H|R1],R2) :- X =< H, imparte(T,X,R1,R2).
imparte([H|T],X,R1,[H|R2]) :- imparte(T,X,R1,R2).