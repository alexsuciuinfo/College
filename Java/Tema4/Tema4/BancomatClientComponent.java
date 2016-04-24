import java.rmi.*;
import java.util.*;
import java.io.*;

public class BancomatClientComponent 
{
    private static final String host = "localhost";
    private static int suma, nr_cont;
    private static String optiune;

    public static void main(String[] args) throws IOException
    {
        try 
        {
            Scanner sc = new Scanner(System.in);
            IBancomat obiect = (IBancomat) Naming.lookup("rmi://" + host + "/Bancomat");
            
            
            do{
            
                 System.out.println("Nr de cont : ");
                 nr_cont = sc.nextInt();
                 if(!obiect.exista_cont(nr_cont))
                     System.out.println("Nr de cont gresit");
            
            }while(!obiect.exista_cont(nr_cont));
            
            System.out.println("Optiune : ");
            System.out.println("retrage suma");
            System.out.println("depune suma");
            System.out.println("verifica");
            System.out.println("exit");
            optiune = sc.next();
            
            while(! optiune.equals("exit"))
            {
                if (optiune.equals("retrage"))
                {
                    suma = sc.nextInt();
                    if(obiect.retragere(suma, nr_cont) == -1)
                        System.out.println("Fonduri insuficente");
                    else System.out.println("Retragerea s-a realizat");
                }
                
                if (optiune.equals("depune"))
                {
                    suma = sc.nextInt();
                    obiect.depunere(suma, nr_cont);
                }
                
                if (optiune.equals("verifica"))
                {
                    System.out.println("Fonduri : " + obiect.afisare(nr_cont));
                }
                
                optiune = sc.next();
            }
        } 
        catch (ConnectException conEx) 
        {
            System.out.println("Nu se poate conecta la server !");
            System.exit(1);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
