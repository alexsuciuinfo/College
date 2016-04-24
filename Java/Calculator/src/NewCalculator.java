import java.rmi.*;
import java.rmi.server.*;


public class NewCalculator extends UnicastRemoteObject implements INewCalculator{

    
    NewCalculator() throws RemoteException {}
    
    @Override
    public ICalculator calc_independent() throws Exception {
        
        return new CalculatorImplementation(0,0);
    }
    
    
    
}
