  import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
import BazaDate.*;

/**
 *
 * @author Alex0x123
 */
public class Delete extends HttpServlet {

    
     
        
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();            
        Query sql = new Query();
        Query sql1 = null;
        int id = 0;
        
         
       try{
        int rez;
        
         String contact = request.getParameter("ID");
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete</title>");  
            out.println("<link rel=stylesheet href=Css.css>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<div id = left>");
            
             out.println("<a  href = index.html>"
                    + "<img src = Agenda.jpg alt = Agenda class = image> </a>");
            
                 
            
            //else out.println("<p> Contacttttt </p>");
            
            out.println("<form method =GET>");
            out.println("ID : <br><input type = \"text\" value = \"\" class = \"but\" name = \"ID\" > <br>");
            out.println("<input type = \"submit\" value = \"Delete\" class = \"sub\">");
            out.println("</form> </div>");
            
       
            out.println("<div id = right>");
            out.println("<table id=\"tabel\">");
             
            out.println(" <tr>\n" +
"    <th>ID</th>\n" +
"    <th>Nume</th>	\n" +
"	<th>Prenume</th>\n" +
"	<th>Telefon mobil</th>\n" +
"	<th>Telefon fix</th>\n" +
"	<th>Email</th>\n" +
"	<th>Adresa</th>\n" +
"	<th>Oras</th>\n" +
"	<th>Judet</th>\n" +
"	<th>Cod postal</th>\n" +
"  </tr>");
            
            
             if(!(contact==null || contact == ""))
          try{
            id = Integer.parseInt(contact);
            rez = sql.deleteID(id);
            if(rez > 0) out.println("<p style=\"color:green ; font-size:20px\"> Contactul a fost sters </p>");
            else out.println(notok());
          }
          catch(NumberFormatException cnfEx){
              out.println("<p style=\"color:red ; font-size:20px\"> Format invalid ! </p>");
          }
         
          if(contact!=null) sql.close();
          sql1 = new Query();
          ResultSet data = sql1.show();
            
            
            try{
        while(data.next()){
            
            out.println(" <tr>\n" +
"    <td>"+data.getInt(1)+"</td>\n" +
"    <td>"+data.getString(2)+"</td>	\n" +
"	<td>"+data.getString(3)+"</td>\n" +
"	<td>"+data.getString(4)+"</td>\n" +
"	<td>"+data.getString(5)+"</td>\n" +
"	<td>"+data.getString(6)+"</td>\n" +
"	<td>"+data.getString(7)+"</td>\n" +
"	<td>"+data.getString(8)+"</td>\n" +
"	<td>"+data.getString(9)+"</td>\n" +
"	<td>"+data.getString(10)+"</td>\n" +
"  </tr>");
            
        }
        }
        
        catch(SQLException sqlEx)
        {
            
            out.println("* Eroare la primirea datelor! *");
            out.println("</div>");
            sqlEx.printStackTrace();
            System.exit(1);
        }
            
            
            out.println("</div> </div></body> </html>");
            
       }finally{
           out.close();
           sql1.close();
       } 
       
    }
    
     
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        
        doGet(request, response);
    }
    
    public Boolean success(String[] v){
        
        String rez = "";  
        if( v[0].equals("") || v[1].equals("") || v[2].equals("") || v[4].equals(""))
            return false;
        return true;
    }
    
    public Boolean Nou(String v[]){
        
        for(int i=0; i<v.length; i++)
            if(!v[i].equals("")) return false;
        return true;
    }
    
    public String ok(){
        
         String rez = "<p style=\"color:green ; font-size:20px\"> Contact adaugat cu succes ! </p>";
         return rez;
    }
    
    public String notok(){
        
         String rez = "<p style=\"color:green ; font-size:20px\"> Nu a fost sters niciun contact ! </p>";
         return rez;
        
    } 

}


