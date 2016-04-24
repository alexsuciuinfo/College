elem([H|T],0,H).
elem([H|T],Nr,R) :- Nr1 is Nr-1, elem(T,Nr1,R).