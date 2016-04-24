color([V,M],Col,R) :- colorez(V,Col,R), \+ test([V,M],R). 

colorez([],_,[]).		
colorez([H|T],Col,[[H,X]|R]) :- member(X,Col), colorez(T,Col,R). 
  
test([V,M],R) :- member([R1,X],R), member([R2,X],R), (member([R1,R2],M) ; member([R2,R1],M)).