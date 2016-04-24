drum(G,X,[X|T],[X|T]).
drum(G,X,[Y|T],R) :- (member([Y1,Y],G); member([Y,Y1],G)), \+ member(Y1,[Y|T]), drum(G,X,[Y1,Y|T],R). 

conex(0,G,_,_).
conex(N,G,X,Nod) :- drum(G,X,[N],R), !, N1 is N-1, X1 is X+1, conex(N1,G,X1,Nod).

nr_muchii([],N,N1) :- N1 is N.
nr_muchii([X|T],N,N2) :- N1 is N+1, nr_muchii(T,N1,N2).

arbore(N,G) :- (conex(N,G,1,N), nr_muchii(G,0,N1), N1 is N-1, write('Este arbore')) ; write('Nu este arbore').