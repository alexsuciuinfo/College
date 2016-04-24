hanoi(1,A,B,_) :- format('~k -> ~k \n',[A,B]). 
hanoi(N,A,B,C) :- N>1, R is N-1, hanoi(R,A,C,B), hanoi(1,A,B,_), hanoi(R,C,B,A).  