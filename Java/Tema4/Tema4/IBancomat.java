import java.rmi.*;

public interface IBancomat extends Remote {
    
    public boolean exista_cont(int nr) throws RemoteException;
    public int retragere(int s, int nr) throws RemoteException;
    public void depunere(int s, int nr) throws RemoteException;
    public int afisare(int nr) throws RemoteException; 
    
}
