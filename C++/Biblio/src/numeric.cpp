#include "numeric.h"
#include <iostream>

using namespace std;

numeric::numeric(unsigned int a)
{
    numar=a;
}

unsigned int numeric::nr()
{
    return numar;
}

void numeric::tipareste()
{
    cout<<numar;
}

numeric numeric::aduna(numeric x)
{
    return numeric(x.nr()+nr());
}


