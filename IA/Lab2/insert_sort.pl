
insert(X,[],X).
insert([H1|T1],[H2|T2],[H1|R]) :- H1 =< H2, insert(H2, T2, R).