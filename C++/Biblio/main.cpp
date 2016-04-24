#include <iostream>
#include <cstring>
#include <complex.h>
#include <SirDeCaractere.h>
#include <adresa.h>
#include <lista.h>
#include <persoana.h>

using namespace std;

char t[100],j[100],o[100],st[100];

int main()
{
    char s[100],a[100],b[100],d[100],e[100],c,ch;
    int nr;
    double x,y;
    persoana pers;
    lista client;
    cout<<"Introduceti numele dumneavoastra : ";
    cin.get(s,100);
    cout<<"====================================================================="<<endl;
    cout<<"Pentru a adauga informatii introduceti 'Adauga'"<<endl;
    cout<<"Pentru a regasi informatii introduceti 'Regasire;"<<endl;
    cout<<"Pentru a sterge informatii introduceti 'Sterge'"<<endl;
    cout<<"Pentru a cauta informatii dupa valoare introduceti 'Cauta'"<<endl;
    cout<<"Pentru a iesi introduceti 'Exit'"<<endl;
    while(stricmp(a,"exit")!=0)
    {
    cout<<"====================================================================="<<endl;
    cout<<"Introduceti optiune : ";
    cin>>a;
    cout<<"====================================================================="<<endl;
    if(stricmp(a,"adauga")==0)
    {
        cout<<"Pentru informatie de tip text introduceti 'T'"<<endl;
        cout<<"Pentru informatie de tip numeric introduceti 'N'"<<endl;
        cout<<"Pentru informatie de tip matematic introduceti 'M'"<<endl;
        cout<<"Pentru informatie de tip adresa introduceti 'A'"<<endl;
        cout<<"======================================================================"<<endl;
        cout<<"Introduceti tipul informatiei : ";
        et:{
            cin>>c;
        cout<<"======================================================================="<<endl;
            switch(c)
            {
            case 'A':
                {cout<<"Nume : "; cin.get(); cin.get(b,100);
                cout<<"Tara : "; cin.get(); cin.get(t,100);
                cout<<"Judet : "; cin.get(); cin.get(j,100);
                cout<<"Oras : "; cin.get(); cin.get(o,100);
                cout<<"Strada : "; cin.get(); cin.get(st,100);
                cout<<"Numar : "; cin>>nr;
                SirDeCaractere nume(b);
                adresa adress(t,j,o,st,nr);
                if(client.parcurge(nume,adress))
                client.push(adress,nume);
                else {cout<<"EROARE"<<endl;
                cout<<"=================================================================="<<endl;}}
                break;
            case 'T':
                {cout<<"Nume : "; cin.get(); cin.get(b,100);
                cout<<"Info : "; cin.get(); cin.get(d,100);
                SirDeCaractere nume(b);
                SirDeCaractere text(d);
                if(client.parcurge(nume,text))
                client.push(text,nume);}
                break;
            case 'N':
                {cout<<"Nume : ";cin.get(); cin.get(b,100);
                cout<<"Info : "; cin>>nr;
                SirDeCaractere nume(b);
                if(client.parcurge(nume,nr))
                client.push(nr,nume);}
                 break;
            case 'M':
                {cout<<"Nume : "; cin.get(); cin.get(b,100);;
                cout<<"Info : "; cin>>x>>ch>>y>>ch;
                complex complx(x,y);
                SirDeCaractere nume(b);
                if(client.parcurge(nume,complx))
                client.push(complx,nume);} break;
            default: cout<<"Ati introdus gresit ! Repetati "; goto et;

            }
    cout<<"========================================================================="<<endl;

        }
    }
    else if(stricmp(a,"sterge")==0)
    {
        cout<<"Pentru a introduce numele informatiei de sters tastati 'N'"<<endl;
        cout<<"Pentru a introduce ID-ul informatiei de sters tastati 'I'"<<endl;
        cout<<"======================================================================"<<endl;
        cout<<"Introduceti optiune : ";
        et1:
            cin>>c;
            cout<<"====================================================================="<<endl;
            switch(c)
            {
                case 'N':
                    {cout<<"Nume : "; cin.get(); cin.get(b,100);
                    SirDeCaractere nume(b);
                    cout<<"======================================================================"<<endl;
                    client.pop(nume);
                    cout<<"======================================================================="<<endl;
                    } break;
                case 'I':
                    {cout<<"ID : "; cin>>nr;
                    cout<<"======================================================================="<<endl;
                    client.pop(nr);
                    cout<<"======================================================================="<<endl;
                    } break;
                default :cout<<"Ati introdus gresit ! Repetati : "; goto et1;

            }
    }
        else if(stricmp("regasire",a)==0)
        {
            cout<<"Pentru a introduce numele informatiei de afisat tastati 'N'"<<endl;
            cout<<"Pentru a introduce ID-ul informatiei de afisat tastati 'I'"<<endl;
            cout<<"======================================================================="<<endl;
            cout<<"Introduceti optiune : ";
            et2:
                cin>>c;
                cout<<"======================================================================="<<endl;
            switch(c)
            {
                case 'N':
                    {cout<<"Nume : "; cin.get(); cin.get(b,100);
                    cout<<"======================================================================="<<endl;
                    SirDeCaractere nume(b);
                    client.regasire_nume(nume);
                    cout<<"======================================================================="<<endl;
                    }break;

                case 'I':
                    {
                        cout<<"ID : "; cin>>nr;
                        cout<<"======================================================================="<<endl;
                        client.regasire_ID(nr);
                        cout<<"======================================================================="<<endl;
                    }break;

                default: cout<<"Ati introdus gresit ! Repetati : "; goto et2;
            }

        }
        else if(stricmp(a,"cauta")==0)
        {
            cout<<"Introduceti valoare : "; cin.get(); cin.get(e,100);
            cout<<"======================================================================"<<endl;
            client.search(e);
            cout<<"======================================================================"<<endl;
        }
    }
    }

