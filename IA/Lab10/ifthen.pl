:- op(800, fx,if).
:- op(700,xfx,then).
:- op(300,xfy,or).
:- op(200,xfy,and).

if hol_ud and baie_uscat then problema_la_bucatarie.
if fereastra_inchisa or fara_ploaie then fara_apa_din_afara.
if problema_la_bucatarie and fara_apa_din_afara then scurgere_la_bucatarie.

fapta(hol_ud).
fapta(fereastra_inchisa).
fapta(baie_uscat).

este_adevarat(P) :- fapta(P).
este_adevarat(P) :- if Conditie then P, este_adevarat(Conditie).

este_adevarat(P and P1) :- este_adevarat(P), este_adevarat(P1).

este_adevarat(P or P1) :- este_adevarat(P); este_adevarat(P1). 