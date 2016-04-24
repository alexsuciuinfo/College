import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

 class Cont{
        
        int nr_cont;
        int suma;
        
        Cont(int nr, int s) {
           
            this.nr_cont = nr;
            this.suma = s;
        }
           
    }

public class BancomatImplementation extends UnicastRemoteObject implements IBancomat {
    
    ArrayList <Cont> cont = new ArrayList <>();
    
    
    public BancomatImplementation() throws RemoteException {
        
        cont.add(new Cont(100,0));
        cont.add(new Cont(101,0));
        cont.add(new Cont(102,0));
        cont.add(new Cont(103,0));
        cont.add(new Cont(104,0));
        
    }

    @Override
    public int retragere(int s, int nr) throws RemoteException {
        
        for (int i=0; i< cont.size(); i++)
            if(cont.get(i).nr_cont == nr)
            {
                if(cont.get(i).suma < s)
                    return -1;
                
                else {
                    cont.get(i).suma -= s;
                    return s;
                }
            }
        return -1;
    }

    @Override
    public void depunere(int s, int nr) throws RemoteException {
      
          for (int i=0; i< cont.size(); i++)
            if(cont.get(i).nr_cont == nr)
            {
                cont.get(i).suma += s;
            }
    }

    @Override
    public int afisare(int nr) throws RemoteException {
       
        for (int i=0; i< cont.size(); i++)
            if(cont.get(i).nr_cont == nr)
            {
                return cont.get(i).suma;
            }
        
        return -1;
    }

    @Override
    public boolean exista_cont(int nr) throws RemoteException {
       
        for(int i=0; i<cont.size(); i++)
            if (cont.get(i).nr_cont == nr) return true;
        return false;
    }  
}
