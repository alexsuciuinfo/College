package BazaDate;


import java.sql.*;

public class BazaDate {
    
    private Connection conn;
    private  Statement state;
    private ResultSet results;
    
    public BazaDate(){}
    
    public Connection Start(){
        
        try
		{
                    Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "acidis123");
                    state = (Statement) conn.createStatement();
		}
                catch(ClassNotFoundException cnfe)
                {
                    System.out.println("* Driverul nu a putut fi incarcat! *");
                    System.exit(1);
                    return null;
                }
		catch(SQLException sqlEx)
		{
			System.out.println("* Conectarea la baza de date a esuat! *");
			System.exit(1);
                        return null;
		}

        return conn;
    }
    
    public void Stop(){
        
        try
		{
			conn.close();
		}
		catch(SQLException sqlEx)
		{
			System.out.println("* Nu m-am putut deconecta! *");
			sqlEx.printStackTrace();
		}
    }
    
    public Statement getState(){
        
        return this.state;
    }
    
    
}
