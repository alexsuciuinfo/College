writeln(X) :- format('~d ',X).

inordine(nil).
inordine(t(S,R,D)) :- inordine(S), writeln(R), inordine(D).

preordine(nil).
preordine(t(S,R,D)) :- writeln(R), preordine(S), preordine(D).

postordine(nil).
postordine(t(S,R,D)) :- postordine(S), postordine(D), writeln(R).