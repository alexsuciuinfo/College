import java.awt.event.ActionEvent;
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.*;

public class ClientSocket {
    
    private static InetAddress host;
    private static final int PORT = 12341;
    static Socket socket = null;
    
    public static void main(String[] args)
	{
                
		try
		{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("\nHost ID not found!\n");
			System.exit(1);
		}
                
                try{
                
                socket = new Socket(host,PORT);
                Thread recieveMess = new Thread(new recieveMess());
                recieveMess.run();
                }
            
                catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
                
	}
    

public static class recieveMess implements Runnable  {
 
    public void run()
    {
	try
	{
			
	Scanner networkInput = new Scanner(socket.getInputStream());
	PrintWriter networkOutput =new PrintWriter(socket.getOutputStream(),true);     
        Scanner sc = new Scanner(System.in);
	String response, response1, nickname = "";
        Boolean ValidNickname = false;
        int nr = 0;
                
        while(!ValidNickname)        
        {
        System.out.println("Choose a nickname : ");
        nickname = sc.nextLine();
        networkOutput.println(nickname);
        response = networkInput.nextLine();
        if(response.equals("invalid"))
            System.out.println("You can't choose this nickname");
        else {
                ValidNickname = true;
            }
        }
        
        System.out.println("Connected as " + nickname);
        
        Thread sendMess = new Thread(new sendMess());
        sendMess.start();
                        
	try{
                            
        while ((response = networkInput.nextLine()) != null)
            {       
                  if(response.equals("list"))          
                  {
                      nr = networkInput.nextInt();
                      for(int i=0; i<=nr; i++)
                      {
                          response1 = networkInput.nextLine();
                          System.out.println(response1);
                      }
                  }
                  
                 else if(response.equals("msg"))
                  {
                      response1 = networkInput.nextLine();
                      System.out.println(response1);
                  }
                  
                else  if(response.equals("bcast"))
                  {
                      response1 = networkInput.nextLine();
                      System.out.println(response1);
                  }
                  
               else    if(response.equals("nick"))
                  {
                      response1 = networkInput.nextLine();
                      System.out.println(response1);
                  }
                   
                   else System.out.println(response);
                   
			}
                            }
                        catch (NoSuchElementException ex){
                            
                        }
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
	}
   }

public static class sendMess implements Runnable  {
  
    public void run()
    {

	try
	{
	PrintWriter networkOutput =new PrintWriter(socket.getOutputStream(),true);  
        Scanner sc = new Scanner(System.in);
	String message = " ";
                        
       do
        {
              message = sc.nextLine();
              networkOutput.println(message);  
                            
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
				System.out.println("\nClosing connection...");
				if(socket.isConnected()) socket.close();
                                System.exit(1);
			}
			catch(IOException ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
   }

}