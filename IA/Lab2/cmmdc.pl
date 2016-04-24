cmmdc(X,0,X).
cmmdc(X,Y,T):-R is X mod Y, cmmdc(Y,R,T).
coprime(X,Y):- cmmdc(X,Y,T), T is 1.
