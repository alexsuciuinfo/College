import java.io.*;
import java.net.*;
import java.util.*;


public class EmailServer
{
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;
    public static int max = 10;
    
    public static class mailbox
    {
       LinkedList <String> message[] = new LinkedList[2];
       int nr_message;
       
       mailbox()
       {
           for(int i=0; i<2; i++)
               message[i] = new LinkedList<String>();
           nr_message = 0;
       }
       
       public void add(String user, String mes)
       {
           if(nr_message<max)
           {
                 message[0].add(user);
                 message[1].add(mes);
                 nr_message++;
           }
       }
       
       public  void read()
       {
          message[0].clear();
          message[1].clear();
          nr_message = 0;
       }
    }
    
    private  static mailbox mailboxD = new mailbox();
    private  static mailbox mailboxK = new mailbox();
    
    
    public static void main(String[] args)
    {
        System.out.println("Opening port…\n");
         
        try
        {
            serverSocket = new ServerSocket(PORT); //Step 1.
        }
        catch(IOException ioEx)
        {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
     
        do
        {
            handleClient();
        }
        while (true);
    }
     
    
    private static void handleClient()
    {
        Socket link = null; //Step 2.
     
        try
        {
            link = serverSocket.accept(); //Step 2.
            Scanner input = new Scanner(link.getInputStream()); //Step 3.
            PrintWriter output = new PrintWriter(link.getOutputStream(),true); //Step 3.
            int numMessages = 0;
            //output.print("User name :");
            String user_name = input.nextLine();
            
            if(user_name.equals("Dave"))
            {
                output.println("Connected as Dave");
                String message = input.nextLine();
            while (!message.equals("quit"))
            {
                System.out.println("Message received.");
                output.println("Action completed");
                int find_send = message.indexOf("send");
                int find_read = message.indexOf("read");
                if(find_send != -1)
                {
                    if(message.indexOf("Dave")==0)
                    {
                        mailboxD.add("Dave",message.substring(9,message.length()));
                    }
                    
                    if(message.indexOf("Karen")==0)
                    {
                        mailboxK.add("Dave",message.substring(10,message.length()));
                    }
                }
                
                if(find_read !=-1)
                {
                    output.println(mailboxD.nr_message);
                    for(int i=0; i<mailboxD.nr_message; i++)
                     output.println("User:" + mailboxD.message[0].get(i)
                        + "; Message: " + mailboxD.message[1].get(i));
                    mailboxD.read();
                }
                   
                message = input.nextLine();
            }
                output.println("Action completed");
            }
                
                if(user_name.equals("Karen"))
            {
                output.println("Connected as Karen");
                String message = input.nextLine();
            while (!message.equals("quit"))
            {
                System.out.println("Message received.");
                output.println("Action completed");
                int find_send = message.indexOf("send");
                int find_read = message.indexOf("read");
                if(find_send != -1)
                {
                    if(message.indexOf("Dave")==0)
                    {
                        mailboxD.add("Karen",message.substring(9, message.length()));
                    }
                    
                    if(message.indexOf("Karen")==0)
                    {
                        mailboxK.add("Karen",message.substring(10, message.length()) );
                    }
                }
            
                if(find_read !=-1)
                {
                        output.println(mailboxK.nr_message);
                    for(int i=0; i<mailboxK.nr_message; i++)
                     output.println("User:" + mailboxK.message[0].get(i)
                        + "; Message: " + mailboxK.message[1].get(i));
                    mailboxK.read();
                             
                }
                message = input.nextLine();
            }
            output.println("Action completed");
        }
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
                link.close(); //Step 5.
            }
            catch(IOException ioEx)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}