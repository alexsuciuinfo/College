import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class CalculatorImplementation extends UnicastRemoteObject implements ICalculator {
    
    private double memorie, rezultat;
   
    
    public  CalculatorImplementation(double mem, double rez) throws RemoteException
    {
        memorie = mem;
        rezultat = rez;
    }
    
    @Override
    public double adunare(double a, double b) throws RemoteException {
        
        rezultat = a+b;
        return rezultat;
    }

    @Override
    public double scadere(double a, double b) throws RemoteException {
        
        rezultat = a-b;
        return rezultat;
    }

    @Override
    public double inmultire(double a, double b) throws RemoteException {
        
        rezultat = a*b;
        return rezultat;
    }

    @Override
    public double impartire(double a, double b) throws RemoteException {
        
        rezultat = a/b;
        return rezultat;  
    }

    @Override
    public double inversare(double a) throws RemoteException {
        
        rezultat = 1/a;
        return rezultat;
    }

    @Override
    public double putere(double a, double b) throws RemoteException {
        
        rezultat = Math.pow(a,b);
        return rezultat;
    }
    
    @Override
    public double factorial(double a) throws RemoteException {
        
        int i; 
        int p = 1;
        if(a < 0) { rezultat = Double.NaN; return rezultat;}
        else if(a == 0) {rezultat = 1; return rezultat;}
        else
        {
          for (i=1 ; i<Math.floor(a) ;i++)
            p*=i;
                rezultat = p;
          return rezultat;
        }
    }

    @Override
    public double radical(double a) throws RemoteException {
        
        rezultat = Math.sqrt(a);
        return rezultat;
    }

    @Override
    public void adunM() throws RemoteException {
        
        memorie+=rezultat;
    }

    @Override
    public void scadM() throws RemoteException {
        
        memorie-=rezultat;
    }

    @Override
    public void stocare() throws RemoteException {
       
        memorie = rezultat;
    }

    @Override
    public void citire(double a) throws RemoteException {
        
        memorie = a;
    }

    @Override
    public void sterge() throws RemoteException {
        
        memorie = 0;
    }

    @Override
    public double rezultat() throws RemoteException {
        
        return rezultat;
    }

    @Override
    public double memorie() throws RemoteException {
    
        return memorie;
    }

    @Override
    public void restoreRez() throws RemoteException {
        
        rezultat = 0;
    }
    
    
}
