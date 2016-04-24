element(X,[H|_]) :- X==H,!.
element(X,[_|T]) :- element(X,T).

inters([],_, []).
inters([H|T], L2, [H|R]) :- element(H, L2), inters(T, L2, R).
inters([_|T], L2, R) :- inters(T, L2, R).

reuniune([],X,X).
reuniune([H|T], L2, R) :- element(H,L2), reuniune(T,L2,R).
reuniune([H|T], L2, [H|R]) :- reuniune(T,L2,R).

dif([],_,[]).
dif([H|T],L2,R) :- element(H,L2), dif(T,L2,R).
dif([H|T],L2,[H|R1]) :- dif(T,L2,R1).
