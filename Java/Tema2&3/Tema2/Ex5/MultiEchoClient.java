import java.awt.event.ActionEvent;
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.*;
import javax.swing.JFrame;

public class MultiEchoClient extends JFrame
{
	private static InetAddress host;
	private static final int PORT = 12341;
        static Socket socket = null;
        
    private static javax.swing.JButton jButtonConnect;
    private static javax.swing.JButton jButtonSend;
    private static javax.swing.JLabel jLabelUser;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTextArea jTextAreachat;
    private static javax.swing.JTextArea jTextAreawrite;
    private static javax.swing.JTextField jTextFieldUser;
    
    public boolean IsConnected = false;
    public static boolean Send = false;
    public boolean Send1 = false;

	public static void main(String[] args)
	{
                
                MultiEchoClient frame = new MultiEchoClient();
                frame.setSize(500,400);
                frame.setVisible(true);
            
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
                        
			String response, nickname;
                        
                        nickname = jTextFieldUser.getText();
                        networkOutput.println(nickname);
                        response = networkInput.nextLine();
                        jTextAreachat.append(response + '\n');
                        
			try{
                            
                        while ((response = networkInput.nextLine()) != null)
			{       
                            
                                String nick [] = response.split(":");
                                if(!nick[0].equals(nickname))
                                jTextAreachat.append(response + '\n');
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
			String message = " ";
                        
			do
			{
                            if(Send == true)
                            {
                            Send = false;
                            message = jTextAreawrite.getText();
                            jTextAreachat.append(message + '\n');
                            networkOutput.println(message);
                            jTextAreawrite.setText("");
                            }
                            
			}while (!message.equals("Bye"));
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
 
        
public MultiEchoClient() {

        jButtonConnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreachat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreawrite = new javax.swing.JTextArea();
        jButtonSend = new javax.swing.JButton();
        jLabelUser = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
         jTextFieldUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserActionPerformed(evt);
            }

            private void jTextFieldUserActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
         
         jLabelUser.setText("Username :");
     
        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });
        
        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }

        });

        jTextAreachat.setColumns(20);
        jTextAreachat.setRows(5);
        jScrollPane1.setViewportView(jTextAreachat);

        jTextAreawrite.setColumns(20);
        jTextAreawrite.setRows(5);
        jScrollPane2.setViewportView(jTextAreawrite);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButtonConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }

private void jButtonConnectActionPerformed(ActionEvent evt) {
                
                if(IsConnected == false)
                
                {
                Thread recieveMess = new Thread(new recieveMess());
                recieveMess.start();     
                IsConnected = true;
                }
                
                else
                {
                    jTextAreachat.append("You are already connected\n");
                }
            }

private void jButtonSendActionPerformed(ActionEvent evt) {
                
                if(IsConnected == true && Send1 == false)
                {
                    Thread sendMess = new Thread(new sendMess());
                    sendMess.start();
                    Send1 = true;
                    Send = true;
                }
                
                else if (IsConnected == false)
                {
                    jTextAreachat.append("You are not connected !\n");
                }
                
                else if(Send == false && IsConnected == true)
                {
                    Send = true;  
                
                }
}
}