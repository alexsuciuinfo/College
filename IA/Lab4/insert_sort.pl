insert_sort([],A,A).
insert_sort([H|T],A,R):-insert(H,A,A1), insert_sort(T,A1,R).

insert(X,[H|T],[H|R]) :- X>H, insert(X,T,R).
insert(X,L,[X|L]).
insert([X],[],[X]).