import java.io.*;
import java.net.*;
import java.util.*;

public class EmailClient
{
    private static InetAddress host;
    private static final int PORT = 1234;
    public static void main(String[] args)
    {
        try
        {
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }
 
    private static void accessServer()
    {
        Socket link = null; //Step 1.
        try
        {
            link = new Socket(host,PORT); //Step 1.
            Scanner input =
            new Scanner(link.getInputStream());
            //Step 2.
            PrintWriter output = new PrintWriter(link.getOutputStream(),true); //Step 2.
             
            //Set up stream for keyboard entry…
            Scanner userEntry = new Scanner(System.in);
            
            String message, response;
            
            System.out.print("Enter user name :");
            message = userEntry.nextLine();
            output.println(message);
            response = input.nextLine(); //Step 3.
            System.out.println("\nSERVER> "+response);
 
            do
            {
                System.out.print("Action: ");
                message = userEntry.nextLine();
                output.println(message); //Step 3.
                response = input.nextLine(); //Step 3.
                System.out.println("\nSERVER> "+response);
                
                if(message.indexOf("read")!=-1)
                {
                    String nr = input.nextLine();
                    int numar = Integer.parseInt(nr);
                    System.out.println("Nr de mesaje" + numar);
                    for(int i=0; i<numar; i++)
                    {
                      response = input.nextLine();
                      System.out.println(response);
                    }
                }
            }while (!message.equals("quit"));
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("\n* Closing connection… *");
                link.close(); //Step 4.
            }
            catch(IOException ioEx)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}