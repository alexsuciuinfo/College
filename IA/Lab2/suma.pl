suma([],0).
suma([H|T],S) :- suma(T,S1), H>0, S is S1+H, !; suma(T,S2), H<0, S is S2.
