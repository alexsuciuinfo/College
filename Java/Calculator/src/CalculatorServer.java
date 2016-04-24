import java.rmi.*;

public class CalculatorServer {
    
    private static final String host = "localhost";

    public static void main(String[] args) throws Exception {
               
        NewCalculator ob = new NewCalculator();       
        String rmiNumeObiect = "rmi://" + host + "/Calculator";
        Naming.rebind(rmiNumeObiect, ob);        
        System.out.println("Binding complete...\n");
    }
}
