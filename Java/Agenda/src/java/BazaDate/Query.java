package BazaDate;

import java.sql.*;

public class Query extends BazaDate {
    
    ResultSet results;
    
    public Query(){
        
        Start();
    
    }
    
    public int adda(String fname, String lname){
        
        int res = 0;
        
        try{
            Statement st = getState();
            String insert = "INSERT INTO contact (nume,prenume) VALUES ('"+fname+"', '"+lname+"')";
            res = st.executeUpdate(insert);
        }
        catch (SQLException e){
            System.out.println("Query couldn't be executed");
            e.printStackTrace();
            System.exit(1);
        }
        
        return res;
    }
    
    public ResultSet searcha(String fname, String lname){
        
        ResultSet res;
            
        try{
            Statement st = getState();
            String select = "SELECT * FROM contact where  '"+fname+"' = nume and '"+lname+"' = prenume";
            res = st.executeQuery(select);
        }
         catch (SQLException e){
            System.out.println("Query couldn't be executed");
            e.printStackTrace();
            System.exit(1);
            res = null;
        }
        
        
        return res;
    }
    
    public int add(String date[]){
        
        int rez = 0;
        
        try{
        Statement st = getState();
     
        String insert = "INSERT INTO contact (nume,prenume,telefon_m,telefon_f,email,adresa,oras,judet,codpostal) "
                                + "VALUES ('"+date[0]+"','"+date[1]+"','"+date[2]+"','"+date[3]+"','"+date[4]+"','"
                                + ""+date[5]+"','"+date[6]+"','"+date[7]+"','"+date[8]+"')";
        
        rez = st.executeUpdate(insert);
        if(rez == 0) System.out.println("Nu s-a putut executa inserarea");
        
    }       
       catch(SQLException sqlEx)
		{
			System.out.println("* Interogarea nu a putut fi executata.! *");
			sqlEx.printStackTrace();
			System.exit(1);
                        
		}
        return rez;
}
    
    public ResultSet show(){
        
            ResultSet rez;
        
        try{
        Statement st = getState();
     
        String select = "SELECT * FROM contact";
        
        rez = st.executeQuery(select);
        
        
    }       
       catch(SQLException sqlEx)
		{
			System.out.println("* Interogarea nu a putut fi executata.! *");
			sqlEx.printStackTrace();
			System.exit(1);
                        rez = null;
                        
		}
        
        return rez;
    }
    
     public ResultSet search(String data[]){
        
            ResultSet rez;
            String vid = "";
        try{
        Statement st = getState();
        
        String select = "SELECT * FROM contact where  "
                + "('"+data[0]+"' = nume and '"+data[0]+"' != '"+vid+"') or"
                + "('"+data[1]+"' = prenume and '"+data[1]+"' != '"+vid+"') or"
                + "('"+data[2]+"' = telefon_m and '"+data[2]+"' != '"+vid+"') or"
                + "('"+data[3]+"' = telefon_f and '"+data[3]+"' != '"+vid+"') or"
                + "('"+data[4]+"' = email and '"+data[4]+"' != '"+vid+"') or"
                + "('"+data[5]+"' = adresa and '"+data[5]+"' != '"+vid+"') or"
                + "('"+data[6]+"' = oras and '"+data[6]+"' != '"+vid+"') or"
                + "('"+data[7]+"' = judet and '"+data[7]+"' != '"+vid+"') or"
                + "('"+data[8]+"' = codpostal and '"+data[8]+"' != '"+vid+"')";
                
        
        rez = st.executeQuery(select);
        
        
    }       
       catch(SQLException sqlEx)
		{
			System.out.println("* Interogarea nu a putut fi executata.! *");
			sqlEx.printStackTrace();
			System.exit(1);
                        rez = null;
                        
		}
        
        return rez;
    }
     
     
     public int delete(String data[]){
        
            int rez = 0;
            String vid = "";
        try{
        Statement st = getState();
        
        String delete = "DELETE FROM contact where  "
                + "('"+data[0]+"' = nume and '"+data[0]+"' != '"+vid+"') or"
                + "('"+data[1]+"' = prenume and '"+data[1]+"' != '"+vid+"') or"
                + "('"+data[2]+"' = telefon_m and '"+data[2]+"' != '"+vid+"') or"
                + "('"+data[3]+"' = telefon_f and '"+data[3]+"' != '"+vid+"') or"
                + "('"+data[4]+"' = email and '"+data[4]+"' != '"+vid+"') or"
                + "('"+data[5]+"' = adresa and '"+data[5]+"' != '"+vid+"') or"
                + "('"+data[6]+"' = oras and '"+data[6]+"' != '"+vid+"') or"
                + "('"+data[7]+"' = judet and '"+data[7]+"' != '"+vid+"') or"
                + "('"+data[8]+"' = codpostal and '"+data[8]+"' != '"+vid+"')";
                
        
        rez = st.executeUpdate(delete);
        
        
    }       
       catch(SQLException sqlEx)
		{
			System.out.println("* Interogarea nu a putut fi executata.! *");
			sqlEx.printStackTrace();
			System.exit(1);
                        rez = 0;
                        
		}
        
        return rez;
    }
     
     public int deleteID(int id){
         
          int rez = 0;
        try{
        Statement st = getState();
        
        String delete = "DELETE FROM contact where ID = '"+id+"' ";
        rez = st.executeUpdate(delete);
        
        
    }       
       catch(SQLException sqlEx)
		{
			System.out.println("* Interogarea nu a putut fi executata.! *");
			sqlEx.printStackTrace();
			System.exit(1);
                        rez = 0;
                        
		}
        
        return rez;
     }
     
     public int update(int id,String date[]){
        
        int rez = 0;
        
        try{
        Statement st = getState();
     
        String insert = "UPDATE contact  set nume = '"+date[0]+"',prenume = '"+date[1]+"',telefon_m ='"+date[2]+"'" 
                + ",telefon_f = '"+date[3]+"', email = '"+date[4]+"' ,adresa = '"+date[5]+"',"
                + "oras = '"+date[6]+"', judet = '"+date[7]+"', codpostal= '"+date[8]+"' "
                + "where id = '"+id+"'";
                               
        
        rez = st.executeUpdate(insert);
        if(rez == 0) System.out.println("Nu s-a putut executa update-ul");
        
    }       
       catch(SQLException sqlEx)
		{
			System.out.println("* Interogarea nu a putut fi executata.! *");
			sqlEx.printStackTrace();
			System.exit(1);
                        
		}
        return rez;
}
     
     
     
     public void close(){
         
         Stop();
     }
     
}