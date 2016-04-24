min(X,Y,X) :- X<Y.
min(X,Y,Y).

min_list([X],X).
min_list([H|T],R) :- min_list(T,R1), R is min(H,R1).