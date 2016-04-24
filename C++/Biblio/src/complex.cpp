#include "complex.h"
#include <iostream>

using namespace std;

complex::complex()
{
    Re=0;
    Im=0;
}

complex::complex(double a)
{
    Re=a;
    Im=0;
}

complex::complex(double a,double b)
{
    Re=a;
    Im=b;
}

double complex::re()
{
    return Re;
}

double complex::im()
{
    return Im;
}

complex operator+(complex &x,complex &y)
{
     return complex(x.re()+y.re(),x.im()+y.im());
}

complex complex::operator=(complex x)
{
    Re=x.re();
    Im=x.im();
    return *this;
}

std::ostream& operator<<(std::ostream &out, const complex &x)
{
 if(x.Re) out<<x.Re;
    if(x.Im>0) out<<"+"<<x.Im<<"i";
    else if(x.Im<0) out<<x.Im<<"i";
return out;
}

std::ostream &operator>>(std::ostream &in, const complex &x)
{char c;
    in>>x.Re>>x.Im;
    return in;
}

