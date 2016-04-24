import java.io.*;
import java.net.*;
import java.util.*;

public class MultiThreadServer
{
	private static ServerSocket serverSocket;
	private static final int PORT = 12341;
        public static ArrayList <Socket> clients = new ArrayList<>();
        
	public static void main(String[] args) throws IOException
	{
		try
		{
			serverSocket = new ServerSocket(PORT);
		}
		catch (IOException ioEx)
		{
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}

		do
		{
			Socket client = serverSocket.accept();
                        clients.add(client);
			System.out.println("\nNew client connected.\n");
			ClientHandler handler = new ClientHandler(clients.get(clients.size()-1));
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
                nickname = input.nextLine();
                output.println("Connected as " + nickname );
                
		do
		{       
                        message = input.nextLine();
			for(int i=0; i<clients.size(); i++)
                        {
                            if(clients.get(i).isConnected())
                            {try
                                {
                                output2 = new PrintWriter(clients.get(i).getOutputStream(),true);
                                if (message.equals("Bye"))
                                    System.out.println("!!!!!!");
                                output2.println(nickname + ": " + message);
                                  }
                            catch(IOException ioEx)
                                {
                                    ioEx.printStackTrace();
                                }
                            }
                            
                        }
	
		}while (!message.equals("Bye"));

		try 
		{
			if (client!=null)
			{
				System.out.println("Closing down connection...");
                                for(int i=0; i<clients.size(); i++)
                                    if(clients.get(i) == client)
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
}
