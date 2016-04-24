import java.rmi.*;

public interface ICalculator extends Remote {

    public double rezultat() throws RemoteException;
    public double memorie() throws RemoteException;
    public double adunare(double a, double b) throws RemoteException;
    public double scadere(double a, double b) throws RemoteException;
    public double inmultire(double a, double b) throws RemoteException;
    public double impartire(double a, double b) throws RemoteException;
    public double inversare(double a) throws RemoteException;
    public double putere(double a, double b) throws RemoteException;
    public double factorial(double a) throws RemoteException;
    public double radical(double a) throws RemoteException;
    public void adunM() throws RemoteException;
    public void scadM() throws RemoteException;
    public void stocare() throws RemoteException;
    public void citire(double a) throws RemoteException;
    public void sterge() throws RemoteException;
    public void restoreRez() throws RemoteException;
       
}
