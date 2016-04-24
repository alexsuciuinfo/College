:-op(800,fx,if).
:-op(700,xfx,then).
:-op(300,xfy,or).
:-op(200,xfy,and).

:- dynamic fapta/1.

fapta(hol_ud).
fapta(baie_uscat).
fapta(fereastra_inchisa).

if hol_ud and baie_uscat then problema_la_bucatarie : 0.9 .
if fereastra_inchisa or fara_ploaie then fara_apa_din_afara.
if fara_apa_din_afara and problema_la_bucatarie then scurgere_la_bucatarie.

inainte :-
	fapta_dedusa_noua(P),!,
	write('Dedus : '), write(P), nl,
	assert(fapta(P)),
	inainte;
	write('Nu mai exista fapte').
	
fapta_compusa(P) :- fapta(P).
fapta_dedusa_noua(P) :-
	if Conditie then P,
	\+(fapta(P)),
	fapta_compusa(Conditie).
	
fapta_compusa(P1 and P2) :-
	fapta_compusa(P1),
	fapta_compusa(P2).
	
fapta_compusa(P1 or P2) :-
	fapta_compusa(P1);
	fapta_compusa(P2).
	
:- op(800,xfx,<=).

este_adevarat(P,P) :- fapta(P).

este_adevarat(P, P <= DemCond) :-
	if Cond then P,
	este_adevarat(Cond,DemCond).
	
este_adevarat(P1 and P2, Dem1 and Dem2) :-
	este_adevarat(P1,Dem1),
	este_adevarat(P2,Dem2).

este_adevarat(P1 or P2, Dem) :-
	este_adevarat(P1,Dem);
	este_adevarat(P2,Dem).
	
certitudine(P,Cert) :- dat(P,Cert).

certitudine(P,Cert) :-
	if Cond then P:C,
	certitudine(Cond,C1),
	Cert is C1 * C.

certitudine(P1 or P2, Cert) :-
	certitudine(P1,Cert1),
	certitudine(P2,Cert2),
	maxim(Cert1,Cert2,Cert).
	
certitudine(P1 and P2, Cert) :-
	certitudine(P1,Cert1),
	certitudine(P2,Cert2),
	minim(Cert1,Cert2,Cert).

minim(X,Y,X) :- X =< Y.
minim(X,Y,Y).

maxim(X,Y,X) :- X > Y.
maxim(X,Y,Y).

dat(hol_ud,1).
dat(baie_uscat,1).
dat(bucatarie_uscat,0).
dat(fereastra_inchisa,0).
dat(fara_ploaie,0.8).