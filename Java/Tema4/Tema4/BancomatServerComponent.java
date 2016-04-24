
import java.rmi.*;

public class BancomatServerComponent {

    private static final String host = "localhost";

    public static void main(String[] args) throws Exception {
               
        BancomatImplementation temporar = new BancomatImplementation();       
        String rmiNumeObiect = "rmi://" + host + "/Bancomat";
        Naming.rebind(rmiNumeObiect, temporar);        
        System.out.println("Binding complete...\n");
    }
}
