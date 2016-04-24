#ifndef SIRDECARACTERE_H
#define SIRDECARACTERE_H
#include <iostream>


class SirDeCaractere
{
    char *s;
public:
    SirDeCaractere();
    SirDeCaractere(char *);
    SirDeCaractere(const SirDeCaractere&);
    ~SirDeCaractere();
    friend SirDeCaractere operator+(const SirDeCaractere&,const SirDeCaractere&);
    friend std::ostream &operator<<(std::ostream&, const SirDeCaractere&);
    friend std::istream &operator>>(std::istream&, const SirDeCaractere&);
    SirDeCaractere operator=(SirDeCaractere);
    int operator==(SirDeCaractere);
    int operator&&(char *);

};

#endif // SIRDECARACTERE_H
