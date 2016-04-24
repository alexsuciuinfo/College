#include "SirDeCaractere.h"
#include <iostream>
#include <cstring>
#include <cstdlib>

using namespace std;

SirDeCaractere::SirDeCaractere()
{
    s=new char[1];
    s='\0';
}

SirDeCaractere::SirDeCaractere(char *p)
{
    s=new char[strlen(p)+1];
    strcpy(s,p);
}

SirDeCaractere::SirDeCaractere(const SirDeCaractere &p)
{
    s=new char[strlen(p.s)+1];
    strcpy(s,p.s);
}

SirDeCaractere::~SirDeCaractere()
{
    delete []s;
}


SirDeCaractere operator+(const SirDeCaractere& s1, const SirDeCaractere &s2)
{
 char *p = new char[strlen(s1.s) + strlen(s2.s) + 1];
 strcpy(p,s1.s);
 strcat(p,s2.s);
 return SirDeCaractere(p);
}

std::ostream &operator<<(std::ostream &out, const SirDeCaractere &s2)
{
    out<<s2.s;
    return out;
}

SirDeCaractere SirDeCaractere::operator=(SirDeCaractere s2)
{
    s=new char[strlen(s2.s)+1];
    strcpy(s,s2.s);
    return *this;
}

int SirDeCaractere::operator==(SirDeCaractere s2)
{
    if(strcmp(s,s2.s)==0) return 1;
    return 0;
}

int SirDeCaractere::operator&&(char s2[])
{
    if(strstr(s,s2)||strstr(s2,s)) return 1;
    return 0;
}

std::istream &operator>>(std::istream &in, const SirDeCaractere &s2)
{
    char sir[100];
    in.get(sir,100);
    strcpy(s2.s,sir);
    return in;

}
