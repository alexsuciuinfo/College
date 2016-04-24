concat([],L,L).
concat([H1|T1],L,[H1|T2]) :- concat(T1,L,T2).

neterminal(nil,[],[]).
neterminal(t(S,R,D),[[R,V1],[R,V2]|T],R) :-  neterminal(S,T1,V1), neterminal(D,T2,V2), concat(T1,T2,T), S\=nil, D\=nil.
neterminal(t(S,R,D),[[R,V1]|T],R) :-  neterminal(S,T1,V1), concat(T1,T,T) ,S\=nil , D==nil.
neterminal(t(S,R,D),[[R,V2]|T],R) :-  neterminal(D,T2,V2), concat(T2,T,T) ,S==nil , D\=nil.
neterminal(t(S,R,D),T,R).

muchii(t(nil,R,nil),[]).
muchii(t(t(S1,R1,D1), R, nil), [[R1,R]]).
muchii(t(nil,R,t(S1,R1,D1)), [[R,R1]]).
muchii(t(t(S1,R1,D1), R, t(S2,R2,D2)),[[R1,R],[R2,R]| T]) :- muchii(t(S1,R1,D1),T1), muchii(t(S2,R2,D2),T2), concat(T1,T2,T).
