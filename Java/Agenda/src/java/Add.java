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
public class Add extends HttpServlet {

    
     
        
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();            
        Query sql = new Query();
        Query sql1 = new Query();
        
        
         
       try{
        int rez;
        
         String contact[] = {request.getParameter("nume"), request.getParameter("prenume"),request.getParameter("telefon_m"),
                            request.getParameter("telefon_f"),request.getParameter("email"),request.getParameter("adresa"),
              request.getParameter("oras"),request.getParameter("judet"),request.getParameter("codpostal")};
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add</title>");  
            out.println("<link rel=stylesheet href=Css.css>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<div id = left>");
            
             out.println("<a  href = index.html>"
                    + "<img src = Agenda.jpg alt = Agenda class = image> </a>");
            
                 
            
            //else out.println("<p> Contacttttt </p>");
            
            out.println("<form method =GET>");
            out.println("Nume : <br><input type = \"text\" value = \"\" class = \"but\" name = \"nume\" > <br>");
            out.println("Prenume : <br> <input type = \"text\" value = \"\" class = \"but\" name = \"prenume\"> <br>");
            out.println("Telefon mobil : <br><input type = \"text\" value = \"\" class = \"but\" name = \"telefon_m\"> <br>");
            out.println("Telefon fix : <br><input type = \"text\" value = \"\" class = \"but\" name = \"telefon_f\"> <br>");
            out.println("Email : <br><input type = \"text\" value = \"\" class = \"but\" name = \"email\"> <br>");
            out.println("Adresa : <br><input type = \"text\" value = \"\" class = \"but\" name = \"adresa\"> <br>");
            out.println("Oras : <br><input type = \"text\" value = \"\" class = \"but\" name = \"oras\"> <br>");
            out.println("Judet : <br><input type = \"text\" value = \"\" class = \"but\" name = \"judet\"> <br>");
            out.println("Cod postal : <br><input type = \"text\" value = \"\" class = \"but\" name = \"codpostal\"> <br>");
            out.println("<input type = \"submit\" value = \"Add\" class = \"sub\">");
            out.println("</form> </div>");
            
            
            if(contact[0]!=null)
            {
                if(success(contact))
            {
                rez = sql.add(contact);
                out.println(ok());
            }
            else
            {
                out.println(notok());
            }
            }
            if(contact[0]!=null)
            sql.close();
            ResultSet data = sql1.show();
            
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
    
     @Override
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
        
         String rez = "<p style=\"color:red ; font-size:20px\">Adaugare esuata! Va rugam sa introduceti campurile obligatorii: </p>"
                 + "<p style=\"color:red ; font-size:20px\" >\n" +
"nume, prenume, adresa, email . </p>";
         return rez;
        
    }  

}