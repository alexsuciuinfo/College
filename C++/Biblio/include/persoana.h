#ifndef PERSOANA_H
#define PERSOANA_H
#include "lista.h"

class persoana
{
    public:
        persoana(char *);
        persoana();
        persoana(const persoana&);
        ~persoana();
        void adaug(const lista &);
    private:
        lista *pers;
        int numar;

};

#endif // PERSOANA_H
