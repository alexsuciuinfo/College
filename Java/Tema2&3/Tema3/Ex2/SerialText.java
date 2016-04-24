import java.util.*;
import java.io.*;

public class SerialText {

    
    public static void main(String[] args) throws IOException {
        
        PrintWriter output = new PrintWriter(new File("names.txt"));
        Persoana[] pers =
			{new Persoana("Andreea"),
			 new Persoana("Mihai"),
                         new Persoana("Alex")};

		for (int i=0; i<pers.length; i++)
			output.println(pers[i].nume);
		output.close();
        
    }
       
}

class Persoana implements Serializable{
    
    String nume;
    
    Persoana(String n)
    {
        this.nume = n;
    }
    
    String getNume()
    {
        return this.nume;
    }
    
}
