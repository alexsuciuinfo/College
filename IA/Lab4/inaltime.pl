max(X,Y,X) :- X>Y.
max(X,Y,Y).

inaltime(nil,0).
inaltime(t(S,R,D),H) :- inaltime(S,H1), inaltime(D,H2), H is max(H1,H2) + 1.