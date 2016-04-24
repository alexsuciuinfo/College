add_inceput([],X,X).
add_inceput(L,X,[X|L]) :- \+ member(X,L).
add_inceput(L,X,L).