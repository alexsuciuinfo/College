import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class FileResults2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        Scanner inp = new Scanner(new File("intrare.txt"));
        ArrayList <Results> res = new ArrayList<Results>();
        int nr = 0;
       
        while(inp.hasNext())
        {
            String s = inp.next();
            if(s.charAt(0) >='0' && s.charAt(0) <='9' )
            {
                int x = Integer.parseInt(s);
                res.get(nr).setMark(x);
                nr++;
            }
            else res.add(new Results(s,0));
        }
        
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("results.dat"));
        
        for(int i=0; i<res.size(); i++)
            output.writeObject(res.get(i));
        
        output.close();
        
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("results.dat"));
             
        try
	{
	   do
		{
                    Results result =(Results)input.readObject();
                    System.out.println(result.getName() + " " + result.getMark());
                            
                }while (true);
        }
        
	catch (EOFException eofEx)
		{
			System.out.println(
				"\n* End of File *");
			input.close();
		}
	}
        
}
    

class Results implements java.io.Serializable{
    
    private String surname;
    private int mark;
    
    public Results(String name, int score)
    {
        surname = name;
        mark = score;
    }
    
    public String getName()
    {
        return surname;
    }
    
    public void setName(String name)
    {
        surname = name;
    }
    
    public int getMark()
    {
        return mark;
    }
    
    public void setMark(int score)
    {
        if(score>=0 && score<=100)
        {
            mark = score;
        }
    }
    
}
