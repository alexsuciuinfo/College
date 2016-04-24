#include "adresa.h"
#include <cstring>
#include <iostream>
#include <cstdlib>

using namespace std;

adresa::adresa()
{
    /*tara=oras=judet=strada=new char[1];
    tara=oras=judet=strada='\0';
    numar=0;*/
}

adresa::adresa(char t[],char j[],char o[],char st[],int nr)
{
   /* tara=new char[strlen(t)+1];
    judet=new char[strlen(j)+1];
    oras=new char[strlen(o)+1];
    strada=new char[strlen(st)+1];*/
    strcpy(tara,t);
    strcpy(judet,j);
    strcpy(oras,o);
    strcpy(strada,st);
    numar=nr;
}

adresa::~adresa()
{
   /* delete []tara;
    delete []judet;
    delete []oras;
    delete []strada; */
}

std::ostream& operator<<(std::ostream &out, const adresa &ad)
{
    out<<"Tara : "<<ad.tara<<", "<<"Judet : "<<ad.judet<<", "<<"Oras : "<<ad.oras<<", "<<"Strada : "<<ad.strada<<", "<<"Numar : "<<ad.numar;
    return out;
}

void adresa:: operator=(const adresa &adress)
{

   /* tara=new char[strlen(adress.tara)+1];
    judet=new char[strlen(adress.judet)+1];
    oras=new char[strlen(adress.oras)+1];
    strada=new char[strlen(adress.strada)+1]; */
    strcpy(tara,adress.tara);
    strcpy(oras,adress.oras);
    strcpy(judet,adress.judet);
    strcpy(strada,adress.strada);
    numar=adress.numar;
}

int adresa:: operator||(char s1[])
{
    if(strstr(tara,s1)||strstr(s1,tara)) return 1;
    if(strstr(judet,s1)||strstr(s1,judet)) return 1;
    if(strstr(oras,s1)||strstr(s1,oras)) return 1;
    if(strstr(strada,s1)||strstr(s1,strada)) return 1;
    if(numar==atoi(s1)) return 1;
    return 0;
}
