import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class Echo extends JFrame implements ActionListener
{
    private JTextField hostInput;
    private JTextField messageInput;
    private JTextArea display;
    private JButton closeButton;
    private JButton sendButton;
    private JButton connectButton;
    private JPanel buttonPanel;
    
    private static Socket link = null;
    
    
    public static void main(String[] args) 
    {
        Echo frame = new Echo();
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent event)
                {
                    if (link != null)     
                    {
                        try
                        {
                            link.close();
                        }
                        catch (IOException ioEx)
                        {
                            System.out.println("\nUnable to close link!\n");
                            System.exit(1);
                        }
                    }
                    System.exit(0); 
                }
            }
        );
        
    }
    
    
      public Echo()
    {
        hostInput = new JTextField(20);
        add(hostInput, BorderLayout.NORTH);
        messageInput = new JTextField(20);
        add(messageInput,BorderLayout.WEST);
        display = new JTextArea(10,15);
        //Following two lines ensure that word-wrapping
        //occurs within the JTextArea…
        display.setWrapStyleWord(true);
        display.setLineWrap(true);
        add(new JScrollPane(display),
        BorderLayout.CENTER);
        buttonPanel = new JPanel();
        connectButton = new JButton("Connect");
        connectButton.addActionListener(this);
        buttonPanel.add(connectButton);
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        buttonPanel.add(sendButton);
        closeButton = new JButton("Close Connection");
        closeButton.addActionListener(this);
        buttonPanel.add(closeButton);
        add(buttonPanel,BorderLayout.SOUTH);
    }
 
    @Override
    public void actionPerformed(ActionEvent event)
    {
        String message,response;
        
        if (event.getSource() == closeButton)
        {
            try
            {
                Scanner input = new Scanner(link.getInputStream());
                PrintWriter output = new PrintWriter(link.getOutputStream(),true);
                message = "***CLOSE***";
                output.println(message); //Step 3.
                response = input.nextLine(); //Step 3.
                display.append("\nSERVER> "+response);
                if (link!=null)
                link.close(); //Close link to host.
            }
            catch(IOException ioEx)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
        
         if (event.getSource() == connectButton)
         {
             try
        {
            String host = hostInput.getText();
            final int HOST_PORT = 7;
            link = new Socket(host, HOST_PORT);         
        }
        catch (UnknownHostException uhEx)
        {
            display.append("No such host!\n");
            hostInput.setText("");
        }
        catch (IOException ioEx)
        {
            display.append(ioEx.toString() + "\n");
        }
         }
         if (event.getSource() == sendButton)
         {
             try
        {   
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);
            
                message = messageInput.getText();
                output.println(message); //Step 3.
                response = input.nextLine(); //Step 3.
                display.append(("\nSERVER> "+response));
                messageInput.setText(""); 
        }
        catch (UnknownHostException uhEx)
        {
            display.append("No such host!\n");
            hostInput.setText("");
        }
        catch (IOException ioEx)
        {
            display.append(ioEx.toString() + "\n");
        }
         }
    }
}
