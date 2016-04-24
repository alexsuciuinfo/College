adauga([H|T],Arb) :- insert(H,Arb,Arb2), adauga(T,Arb2).
adauga([],Arb) :- write(Arb).

insert(X,nil,t(nil,X,nil)).
insert(X,t(S,R,D),R1) :- X=<R, insert(X,S,R2), R1 = t(R2,R,D).
insert(X,t(S,R,D),R1) :- X>R, insert(X,D,R2), R1 = t(S,R,R2).