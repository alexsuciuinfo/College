#ifndef LISTA_H
#define LISTA_H
#include "complex.h"
#include "numeric.h"
#include "SirDeCaractere.h"
#include "adresa.h"

class lista:public SirDeCaractere
{
    public:
        lista();
        lista(char *);
        ~lista();
        int vid();
        void push(int & ,SirDeCaractere&);
        void push(complex &,SirDeCaractere&);
        void push(SirDeCaractere& ,SirDeCaractere&);
        void push(adresa &,SirDeCaractere&);
        void pop(SirDeCaractere&);
        void pop(int);
        void search(char []);
        void regasire_ID(int);
        void regasire_nume(SirDeCaractere&);
        int parcurge(SirDeCaractere&,complex&);
        int parcurge(SirDeCaractere&,SirDeCaractere&);
        int parcurge(SirDeCaractere&,adresa&);
        int parcurge(SirDeCaractere&,int);
    private:
        struct nod
        {
            int ID;
            int nr;
            complex *cl;
            SirDeCaractere *sir,*nume;
            adresa *ads;
            nod *next,*back;
            int tip;
        };
        nod *p,*u;
        char *NUME;
};

#endif // LISTA_H
