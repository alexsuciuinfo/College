layout_binary_tree(T,PT) :- layout_binary_tree(T,PT,1,_,1).

layout_binary_tree(nil,nil,I,I,_).
layout_binary_tree(t(L,W,R),t(W,X,Y,PL,PR),Iin,Iout,Y) :- 
   Y1 is Y + 1,
   layout_binary_tree(L,PL,Iin,X,Y1), 
   X1 is X + 1,
   layout_binary_tree(R,PR,X1,Iout,Y1).
