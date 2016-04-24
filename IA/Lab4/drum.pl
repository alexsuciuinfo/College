drum(G,X,[X|T],[X|T]).
drum(G,X,[Y|T],R) :- (member([Y1,Y],G); member([Y,Y1],G)), \+ member(Y1,[Y|T]), drum(G,X,[Y1,Y|T],R).