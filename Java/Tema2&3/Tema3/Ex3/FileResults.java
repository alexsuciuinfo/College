import java.io.*;
import java.util.*;

public class FileResults{
    
    private static final int REC_SIZE = 20;
    private static final int SURNAME_SIZE = 15;
    private static String surnmae;
    private static int mark;
    static ArrayList <String> names_marks = new ArrayList<String>();
    static RandomAccessFile file;
    
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(new File("intrare.txt"));
        file = new RandomAccessFile("results.dat","rw");
        
        while(input.hasNext())
        {
            names_marks.add(input.next());
        }
        
        for(int i=0; i<names_marks.size()/2 ;i++)
        {
            writeString(file,names_marks.get(i),SURNAME_SIZE);
            writeString(file,names_marks.get(names_marks.size()/2+i),REC_SIZE);
            
        }
        
        file.seek(0);
        
        while(file.getFilePointer() != file.length())     
        {
        System.out.print(readString(file,SURNAME_SIZE));
            System.out.println(readString(file,REC_SIZE));
        
        }
    }
    
    public static void writeString(RandomAccessFile file, String text, int fixedSize) throws IOException
    {
        int size = text.length();
        if(size <= fixedSize)
        {
            file.writeChars(text);
            for(int i = size; i<fixedSize; i++)
            {
                file.writeChar(' ');
                
            }
        }
        
        else
        {
            file.writeChars(text.substring(0,fixedSize));
        }
                
    }
    
    public static String readString(RandomAccessFile file, int fixedSize) throws IOException
    {
        String value = "";
        for(int i=0; i<fixedSize; i++)
        {
            value += file.readChar();
        }
        
        return value;
    }
    
    
}
