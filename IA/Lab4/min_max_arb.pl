mini([X|Y],Z,R) :- R is min(min(X,Y),Z).
maxi([X|Y],Z,R) :- R is max(max(X,Y),Z).

min_arb([[X,Y]],X) :- X<Y.
min_arb([[X,Y]],Y).
min_arb([H|T],Min) :- min_arb(T,Min1), mini(H,Min1,Min).

max_arb([[X,Y]],X) :- X>Y.
max_arb([[X,Y]],Y).
max_arb([H|T],Max) :- max_arb(T,Max1), maxi(H,Max1,Max).