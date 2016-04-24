selectie([H1|T1],[H2|T2]) :- min_list([H1|T1],H2), sterge([H1|T1],H2,R), selectie(R,T2).
selectie([],[]).

min1(X,Y,X) :- X<Y.
min1(X,Y,Y).
min_list([X],X).
min_list([H|T],R) :- min_list(T,R1), R is min1(H,R1).

sterge([],_,[]).
sterge([H|T],X,T) :- H==X.
sterge([H|T],X,[H|R]) :- sterge(T,X,R).
