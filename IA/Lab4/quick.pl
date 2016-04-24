quick_sort([H|T],R) :- imparte(T,H,S,D), quick_sort(S,S1), quick_sort(D,D1), concat(S1,[H|D1],R).
quick_sort([],[]).

concat([],L,L).
concat([H1|T1],L,[H1|T2]) :- concat(T1,L,T2).

imparte([],X,[],[]).
imparte([H|T],X,[H|R1],R2) :- X > H, imparte(T,X,R1,R2).
imparte([H|T],X,R1,[H|R2]) :- imparte(T,X,R1,R2).