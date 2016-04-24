elimina(_,[],[]).
elimina(X,[H|Tail],T):- X < H,elimina(X,Tail,T).
elimina(X,[H|Tail],[H|T]):-elimina(X,Tail,T).