import java.io.*;
import java.net.*;
import java.util.*;

public class ServerSocket
{
	private static java.net.ServerSocket serverSocket;
	private static final int PORT = 12341;
        public static ArrayList <Client> clients = new ArrayList<>();
       // public static ArrayList <String> nicknames = new ArrayList<>();
        
        
	public static void main(String[] args) throws IOException
	{
		try
		{
			serverSocket = new java.net.ServerSocket(PORT);
                        
		}
		catch (IOException ioEx)
		{
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}

		do
		{       
			Socket client = serverSocket.accept();
                        clients.add(new Client(client, " "));
			System.out.println("\nNew client connected.\n");
			ClientHandler handler = new ClientHandler(clients.get(clients.size()-1).client);
			handler.start();
		
                }while (true);
	}
        
        
       public static class ClientHandler extends Thread
{
        private Socket client;
        private Scanner input, inpu2;
	private PrintWriter output, output2;
           
	public ClientHandler(Socket socket)
	{
		client = socket;
              
		try
		{
                    input = new Scanner(client.getInputStream());
                    output = new PrintWriter(client.getOutputStream(),true);
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
	}

	public void run()
	{
		String nickname,message;
                String messagetext = "", nick = " ";
                Boolean isvalid = true;
                int i;
                
                do
                {
                    isvalid = true;
                    nickname = input.nextLine();
                    if(nickname.contains(" ")) isvalid = false;
                    for(i=0; i<clients.size(); i++)
                        if(clients.get(i).nickname.equals(nickname))
                            isvalid = false;
                    if(isvalid) output.println("valid");
                    else output.println("invalid");
                
                }while (!isvalid);
                
                for(i=0; i<clients.size(); i++)
                    if(clients.get(i).nickname.equals(" ") && clients.get(i).client == client)
                        clients.get(i).nickname = nickname;
                
                
		do
		{       
                        message = input.nextLine();
                        
                    if(message.indexOf("list") == 0)
                    {
                        int nr = 0;
                        output.println("list");
                        for(i=0; i<clients.size(); i++)
                        {
                            if(!clients.get(i).nickname.equals(" ")) nr++;
                        }
                        output.println(nr);
                        System.out.println(clients.size());
                        System.out.println(nr);
                        
                        for(i=0; i<clients.size(); i++)
                        {
                            if(!clients.get(i).nickname.equals(" ")) 
                                output.println(clients.get(i).nickname);
                        }
                    }
                    
                   else if(message.indexOf("msg") == 0)
                    {
                        Boolean ok = false;
                        for(i=0; i<clients.size(); i++)
                            if(message.indexOf(clients.get(i).nickname) == 4)
                            {
                                ok = true;
                                try
                                {
                                message = message.replaceFirst("msg","");
                                message = message.replaceFirst(clients.get(i).nickname,"");
                                output2 = new PrintWriter(clients.get(i).client.getOutputStream(),true);
                                output2.println("msg");
                                output2.println(nickname + " :" + message);
                                }
                                catch(IOException ioEx)
                                {
                                    ioEx.printStackTrace();
                                }
                            }
                        if(!ok) 
                        {   
                            output.println("msg");
                            output.println("There is no user connected with this username");
                        }
                    }
                    
                   else if(message.indexOf("bcast") == 0)
                    {
                        for(i=0; i<clients.size(); i++)
                        {
                            if(!clients.get(i).nickname.equals(" ") && !clients.get(i).nickname.equals(nickname))
                                try{
                                
                                
                                messagetext = message.replaceFirst("bcast","");
                                output2 = new PrintWriter(clients.get(i).client.getOutputStream(),true);
                                output2.println("bcast");
                                output2.println(nickname + " :" + messagetext);
                                    
                                }
                                    catch(IOException ioEx)
                                {
                                    ioEx.printStackTrace();
                                }
                        }
                    }
                    
                   else if(message.indexOf("nick") == 0)
                   {
                       Boolean ok = true;
                       
                       nick = message.replaceFirst("nick","");
                       System.out.println(".." + nick);
                       nick = nick.replace(" ", "");
                       System.out.println(".." + nick);
                       output.println("nick");
                       for(i=0; i<clients.size(); i++)
                           if(clients.get(i).nickname.equals(nick)) ok = false;
                       if(ok == true)
                       {
                           for(i=0; i<clients.size(); i++)
                           if(clients.get(i).nickname.equals(nickname))
                               clients.get(i).nickname = nick;
                           nickname = nick;
                           output.println("Nickname successfully changed");
                           
                       }
                       else output.println("Nickname cannot be changed, because there's already an user with this nickname");
                                                    
                   }
                    
                    else
                   {
                       if(message.indexOf("quit") != 0)
                       output.println("Invalid command");
                   }
	
		}while (!message.equals("quit"));

		try 
		{
			if (client!=null)
			{
				System.out.println("Closing down connection...");
                                for(i=0; i<clients.size(); i++)
                                    if(clients.get(i).client == client)
                                        clients.remove(i);
				client.close();
                                
			}
		}
		catch(IOException ioEx)
		{
			System.out.println("Unable to disconnect!");
		}
	}
}
       
     static class Client{
         
         Socket client;
         String nickname;
         
         Client(Socket c, String nick)
         {
             client = c;
             nickname = nick;
         }
         
     }
}
