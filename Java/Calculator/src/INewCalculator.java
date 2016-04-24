import java.rmi.*;

public interface INewCalculator extends Remote{
    
    ICalculator calc_independent() throws Exception;
    
}
