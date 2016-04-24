#include "lista.h"
#include <iostream>
#include <cstring>
#include <cstdlib>
#include <conio.h>

using namespace std;

lista::lista()
{
    p=u=NULL;
}

lista::lista(char *s)
{
    p=u=NULL;
    NUME=new char[strlen(s)+1];
    strcpy(NUME,s);
}

void lista::push(complex &x,SirDeCaractere &s)
{
    if(!p)
    {

        p=new nod;
        p->ID=1;
        p->tip=1;
        p->cl=new complex;
        p->cl[0]=x;
        p->nume=new SirDeCaractere(s);
        p->next=NULL;
        p->back=NULL;
        u=p;
        cout<<"ID : "<<1<<endl;
    }
    else
    {
        nod *nou=new nod;
        nou->cl=new complex;
        nou->cl[0]=x;
        nou->nume=new SirDeCaractere(s);
        nou->next=NULL;
        u->next=nou;
        nou->back=u;
        u=nou;
        nou->ID=nou->back->ID+1;
        nou->tip=1;
        cout<<"ID : "<<nou->ID<<endl;
    }
}

void lista::push(SirDeCaractere &x,SirDeCaractere &s)
{
    if(!p)
    {
        p=new nod;
        p->ID=1;
        p->tip=2;
        p->sir=new SirDeCaractere(x);
        p->nume=new SirDeCaractere(s);
        p->next=NULL;
        p->back=NULL;
        u=p;
        cout<<"ID : "<<1<<endl;
    }
    else
    {
        nod *nou=new nod;
        nou->sir=new SirDeCaractere(x);
        nou->nume=new SirDeCaractere(s);
        nou->next=NULL;
        u->next=nou;
        nou->back=u;
        u=nou;
        nou->ID=nou->back->ID+1;
        nou->tip=2;
        cout<<"ID : "<<nou->ID<<endl;
    }
}

void lista::push(adresa &x,SirDeCaractere &s)
{
    if(!p)
    {

        p=new nod;
        p->ID=1;
        p->tip=3;
        p->ads=new adresa;
        p->ads[0]=x;
        p->nume=new SirDeCaractere(s);
        p->next=NULL;
        p->back=NULL;
        u=p;
        cout<<"ID : "<<1<<endl;
    }
    else
    {
        nod *nou=new nod;
        nou->ads=new adresa(x);
        nou->nume=new SirDeCaractere(s);
        nou->next=NULL;
        u->next=nou;
        nou->back=u;
        u=nou;
        nou->ID=nou->back->ID+1;
        nou->tip=3;
        cout<<"ID : "<<nou->ID<<endl;
    }
}

void lista::push(int &x,SirDeCaractere &s)
{
    if(!p)
    {
        p=new nod;
        p->tip=4;
        p->ID=1;
        p->nr=x;
        p->nume=new SirDeCaractere(s);
        p->next=NULL;
        p->back=NULL;
        u=p;
        cout<<"ID : "<<1<<endl;
    }
    else
    {
        nod *nou=new nod;
        nou->nr=x;
        nou->nume=new SirDeCaractere(s);
        nou->next=NULL;
        u->next=nou;
        nou->back=u;
        u=nou;
        nou->ID=nou->back->ID+1;
        nou->tip=4;
        cout<<"ID : "<<nou->ID<<endl;
    }
}

int lista::vid()
{
    return p==NULL;
}


lista::~lista()
{
    delete []p;
}

void lista::regasire_nume(SirDeCaractere &sir)
{
    nod *nou;
    if (vid()) {cout<<"Nu s-au gasit informatii"; return;}
    nou=p;
    while(nou)
    {
        if(nou->nume[0]==sir)
        {
            if(nou->tip==1) {cout<<nou->nume[0]<<" : "<<nou->cl[0]<<endl; return;}
            else if(nou->tip==3) {cout<<nou->nume[0]<<" : "<<nou->ads[0]<<endl; return;}
            else if(nou->tip==2) {cout<<nou->nume[0]<<" : "<<nou->sir[0]<<endl; return;}
            else {cout<<nou->nume[0]<<" : "<<nou->nr<<endl; return;}
        }
        nou=nou->next;
    }
    cout<<"Nu s-au gasit informatii"<<endl;
}

void lista::regasire_ID(int x)
{
    nod *nou;
    if(vid()) {cout<<"Nu s-au gasit informatii"; return;}
    nou=p;
    while(nou)
    {
        if(nou->ID==x)
        {
            if(nou->tip==1) {cout<<nou->nume[0]<<" : "<<nou->cl[0]<<endl; return;}
            else if(nou->tip==3) {cout<<nou->nume[0]<<" : "<<nou->ads[0]<<endl; return;}
            else if(nou->tip==2) {cout<<nou->nume[0]<<" : "<<nou->sir[0]<<endl; return;}
            else {cout<<nou->nume[0]<<" : "<<nou->nr<<endl; return;}
        }
        nou=nou->next;
    }
    cout<<"Nu s-au gasit informatii"<<endl;
}

int lista::parcurge(SirDeCaractere &s1,complex &x)
{
    nod *nou;
    if(vid()) return 1;
    nou=p;
    while(nou)
    {
        if(nou->nume[0]==s1)
        {
            nou->cl[0]=nou->cl[0]+x;
            return 0;
        }
        nou=nou->next;
    }
    return 1;
}

int lista::parcurge(SirDeCaractere &s1,adresa &x)
{
    nod *nou;
    if(vid()) return 1;
    nou=p;
    while(nou)
    {
        if(nou->nume[0]==s1)
        {
            return 0;
        }
        nou=nou->next;
    }
    return 1;
}

int lista::parcurge(SirDeCaractere &s1,SirDeCaractere &s2)
{
    nod *nou;
    if(vid()) return 1;
    nou=p;
    while(nou)
    {
        if(nou->nume[0]==s1)
        {
            nou->sir[0]=nou->sir[0]+s2;
            return 0;
        }
        nou=nou->next;
    }
    return 1;
}

int lista::parcurge(SirDeCaractere &s1,int x)
{
    nod *nou;
    if(vid()) return 1;
    nou=p;
    while(nou)
    {
        if(nou->nume[0]==s1)
        {
            nou->nr=nou->nr+x;
            return 0;
        }
        nou=nou->next;
    }
    return 1;
}

void lista::pop(SirDeCaractere &s1)
{

    nod *aux,*nou;
    nou=p;
    if(vid()) {cout<<"Nu s-au gasit informatii de sters cu acest nume"<<endl; return;}
    if(p->nume[0]==s1 && p==u)
    {
        p=u=NULL;
        cout<<"Informatie stearsa"<<endl;
        return;
    }
    if(p->nume[0]==s1)
    {
        aux=p;
        p=p->next;
        p->back=0;
        delete aux;
        cout<<"Informatie stearsa"<<endl;
        return;
    }
    if(u->nume[0]==s1)
    {
    nou=u;
    u->back->next=NULL;
    delete nou;
    return;
    }
    else
    {
        while(nou!=NULL && !(nou->nume[0]==s1))
        {
            nou=nou->next;
        }
        if(nou!=NULL)
        {aux=nou;
        nou->back->next=nou->next;
        delete aux;
        cout<<"Informatie stearsa"<<endl;
        return;
    }}
    cout<<"Nu s-au gasit informatii cu acest nume"<<endl;
}

void lista::pop(int x)
{

    nod *aux,*nou;
    nou=p;
    if(vid()) {cout<<"Nu s-au gasit informatii de sters cu acest ID"<<endl; return;}
    if(p->ID==x && p==u)
    {
        p=u=NULL;
        cout<<"Informatie stearsa";
        return;
    }
    if(p->ID==x)
    {
        aux=p;
        p=p->next;
        p->back=NULL;
        delete aux;
        cout<<"Informatie stearsa"<<endl;
        return;
    }
    if(u->ID==x)
    {
    nou=u;
    u->back->next=NULL;
    delete nou;
    return;
    }
    else
    {
        while(nou!=NULL && !(nou->ID==x))
        {
            nou=nou->next;
        }
        if(nou!=NULL)
        {aux=nou;
        nou->back->next=nou->next;
        delete aux;
        cout<<"Informatie stearsa"<<endl;
        return;
    }}
    cout<<"Nu s-au gasit informatii cu acest ID"<<endl;
}

void lista::search(char x[])
{
    nod *nou; int ok=1;
    if(vid()) {cout<<"Nu s-au gasit informatii "<<endl; return;}
    nou=p;
//    clrscr();
    while(nou)
    {
        if(nou->nume[0]&&x)
        {
            if(nou->tip==1) cout<<nou->nume[0]<<" : "<<nou->cl<<endl;
            if(nou->tip==2) cout<<nou->nume[0]<<" : "<<nou->sir<<endl;
            if(nou->tip==3) cout<<nou->nume[0]<<" : "<<nou->ads[0]<<endl;
            else cout<<nou->nume[0]<<" : "<<nou->nr<<endl;
            ok=0;
        }
        else if(nou->tip==2 && (nou->sir[0]&&x)) {cout<<nou->nume[0]<<" : "<<nou->sir[0]<<endl; ok=0;}
        else if(nou->tip==3 && (nou->ads[0]||x)) {cout<<nou->nume[0]<<" : "<<nou->ads[0]<<endl; ok=0;}
        else if(nou->tip==4 && nou->nr==atoi(x)) {cout<<nou->nume[0]<<" : "<<nou->nr<<endl; ok=0;}
        nou=nou->next;
    }
    if(ok) cout<<"Nu s-au gasit informatii "<<endl;
}


