duplicat([]) :- write('NO').
duplicat([H|T]) :- member(H,T), write('YES'), ! ; duplicat(T).