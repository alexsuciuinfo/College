#include "persoana.h"
#include <iostream>
#include <cstring>

using namespace std;

persoana::persoana()
{
    numar=0;
    pers=new lista[20];
}

void persoana::adaug(const lista &client)
{
    pers[numar++]=client;
}

persoana::~persoana()
{
    delete []pers;
    numar=0;
}
