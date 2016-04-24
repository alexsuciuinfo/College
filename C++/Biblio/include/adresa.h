#ifndef ADRESA_H
#define ADRESA_H
#include<iostream>


class adresa
{
    char tara[100],judet[100],oras[100],strada[100];
    int numar;
public:
    adresa(char *,char *,char *,char *,int);
    adresa();
    ~adresa();
    friend std::ostream &operator<<(std::ostream&,const adresa&);
    int operator||(char *);
    void operator=(const adresa&);
};

#endif // ADRESA_H
