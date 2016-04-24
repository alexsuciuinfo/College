concat([],L,L).
concat([H1|T1],L,[H1|T2]) :- concat(T1,L,T2).

terminal(nil,[]).
terminal(t(S,R,D),[R|T]) :- S = nil, D = nil.
terminal(t(S,R,D),T) :- terminal(S,T1), terminal(D,T2), concat(T1,T2,T).

neterminal(nil,[]).
neterminal(t(S,R,D),[R|T]) :- neterminal(S,T1), neterminal(D,T2), concat(T1,T2,T), (S\=nil ; D\=nil) .
neterminal(t(S,R,D),T).



