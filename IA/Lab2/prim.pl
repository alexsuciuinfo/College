divizibil(X,Y):- Y < X, X mod Y =:= 0.
divizibil(X,Y):- Y < X, R is Y+1, divizibil(X,R).
prim(X):- Y is 2, X > 1, \+divizibil(X,Y).