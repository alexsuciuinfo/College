insert(L,X,0,[X|L]).
insert([H|L],X,P,[H|R]):- P>0, !, P1 is P-1, insert(L,X,P1,R). 
