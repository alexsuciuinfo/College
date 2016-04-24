sterge(nil,X,nil).
sterge(t(nil,R,D),R,D).
sterge(t(S,R,nil),R,S).
sterge(t(S,R,D),R,T1) :- max_st(S,Max,S1), T1 = t(S1,Max,D).
sterge(t(S,R,D),X, t(T2,R,D)) :- X<R, sterge(S,X,T2).
sterge(t(S,R,D),X,t(S,R,T2)) :- sterge(D,X,T2).

max_st(t(S,Max,nil),Max,S).
max_st(t(S,R,D),Max,R1) :- max_st(D,Max,R2), R1 = t(S,R,R2).