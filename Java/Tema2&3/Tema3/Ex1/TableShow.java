import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class TableShow extends JFrame {
    
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTable;
 
    public static void main(String[] args) throws IOException {
     
        
        Scanner input = new Scanner(new File("intrare.txt"));
        
        Vector <String> pay = new Vector<String>();
        Vector <String> sur = new Vector<String>();
        int nr_surnames = 0, nr_payrolls = 0;
        
        while(input.hasNext())
        {
            String s = input.next();
            if(s.charAt(0) >='0' && s.charAt(0) <='9')
            {
                pay.add(s);
            }
            
            else sur.add(s);
        }
        
        String rows[][] = new String[sur.size()][pay.size()];
        for(int i=0; i<sur.size(); i++)
            rows[i][0] = sur.get(i);
        
        for(int i=0; i<pay.size(); i++)
            rows[i][1] = pay.get(i);
        
        TableShow frame = new TableShow(rows);
        frame.setSize(300,400);
        frame.setVisible(true);
        
    }
    
    
 public TableShow(String rows[][])
 {
             jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            rows,
            new String [] {
                "Surname", "Payroll No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(300, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addGap(77, 77, 77))
        );

        pack();

 }
    
}
