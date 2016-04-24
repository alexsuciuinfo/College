nrelem([],X) :- write(X).
nrelem([H|T],R) :-  R1 is R+1, nrelem(T,R1).