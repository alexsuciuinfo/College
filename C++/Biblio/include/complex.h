#ifndef COMPLEX_H
#define COMPLEX_H
#include <iostream>

class complex
{
    double Re,Im;
public:
    complex(double,double);
    complex();
    complex(double);
    double re();
    double im();
    friend complex operator +( complex&, complex &);
    complex operator=(complex);
    friend std::ostream &operator>>(std::ostream&, const complex&);
    friend std::ostream &operator<<(std::ostream&, const complex&);

};
#endif
