bubble_sort(L1,R) :- inter(L1,L2), !, bubble_sort(L2,R).
bubble_sort(L,L).

inter([A,B|T],[B,A|T]) :- A>B.
inter([A|T1],[A|T2]) :- inter(T1,T2).