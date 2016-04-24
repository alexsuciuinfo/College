import java.rmi.*;
import java.util.*;
import java.io.*;

public class CalculatorClient {
    
    private static final String host = "localhost";
    
    
    public static Boolean numar(String s)
    {
        int nr = 0,i;
        
        if(s.charAt(0) == '-')
        {
            for(i=1; i<s.length(); i++)
        {
            if(!(('0' <= s.charAt(i) && s.charAt(i) <= '9') || (s.charAt(i) == '.'))) return false;
            if(s.charAt(i) == '.') nr++;
        }
        
        if(nr > 1) return false;
        return true;
        }
        else
            {
            for(i=0; i<s.length(); i++)
        {
            if(!(('0' <= s.charAt(i) && s.charAt(i) <= '9') || (s.charAt(i) == '.'))) return false;
            if(s.charAt(i) == '.') nr++;
        }
        
        if(nr > 1) return false;
        return true;
        }
    }
    
    public static void main(String z[]) throws IOException
    {
        
        Scanner sc = new Scanner(System.in);
        String optiune = "", val="";
        INewCalculator obiect = null;
        ICalculator calc = null;
        
         try 
        {
           obiect = (INewCalculator) Naming.lookup("rmi://" + host + "/Calculator");
        } 
        catch (ConnectException conEx) 
        {
            System.out.println("Nu se poate conecta la server !");
            System.exit(1);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            System.exit(1);
        }
         
         try{
             calc = obiect.calc_independent();
         }
         catch (Exception e) {}
         
            System.out.println("Meniu :");
            System.out.println("1. Adunare : '+' ");
            System.out.println("2. Scadere : '-' ");
            System.out.println("3. Inmultire : '*' ");
            System.out.println("4. Impartire : '/' ");
            System.out.println("5. Inversare : 'inv' ");
            System.out.println("6. Ridicare la putere : '^' ");
            System.out.println("7. Factorial : '!' ");
            System.out.println("8. Radacina patrata : 'sqrt' ");
            System.out.println("9. Exit : 'exit' " );
            
            System.out.println("Op Memorie : ");
            System.out.println("10. adunare : 'M+' ");
            System.out.println("11. scadere : 'M-' ");
            System.out.println("12. stocare: 'MS' ");
            System.out.println("13. citire : 'MC' ");
            System.out.println("14. stergere : 'MD' ");
            
            System.out.println("Alege optiune :");
            System.out.println("Rez = 0");
            
            while(!optiune.equals("exit"))
        {
                optiune = sc.next();
            try{
            
                switch(optiune)
                {
                 
                    case "+":
                    {
                        val = sc.next();
                        if(numar(val))
                            calc.adunare(calc.rezultat(),Double.parseDouble(val));
                        else if(val.equals("MC"))
                            calc.adunare(calc.rezultat(),calc.memorie());
                                    else {
                            
                            System.out.println("Introduceti un numar valid!");
                            calc.restoreRez();
                        }
                    } break;
                        
                    case "-":
                    {
                        val = sc.next();
                        if(numar(val))
                            calc.scadere(calc.rezultat(),Double.parseDouble(val));
                            else if(val.equals("MC"))
                            calc.scadere(calc.rezultat(),calc.memorie());
                                    else {
                                System.out.println("Introduceti un numar valid!");
                                calc.restoreRez();
                            }
                    } break;
                        
                    case "*":
                    {
                        val = sc.next();
                        if(numar(val))
                        calc.inmultire(calc.rezultat(),Double.parseDouble(val));
                        else if(val.equals("MC"))
                            calc.inmultire(calc.rezultat(),calc.memorie());
                                    else {
                            System.out.println("Introduceti un numar valid!");
                            calc.restoreRez();
                        }
                    } break;
                        
                    case "/":
                    {
                        val = sc.next();
                        if(numar(val))
                        calc.impartire(calc.rezultat(),Double.parseDouble(val));
                        else if(val.equals("MC"))
                            calc.impartire(calc.rezultat(),calc.memorie());
                                    else {
                            System.out.println("Introduceti un numar valid!");
                            calc.restoreRez();
                        }
                    } break;
                        
                        
                    case "inv":
                    {
                        calc.inversare(calc.rezultat());
                        
                    } break;
                        
                        
                    case "^":
                    {
                        val = sc.next();
                        if(numar(val))
                        calc.putere(calc.rezultat(),Double.parseDouble(val));
                        else if(val.equals("MC"))
                            calc.putere(calc.rezultat(),calc.memorie());
                                    else {
                            System.out.println("Introduceti un numar valid!");
                            calc.restoreRez();
                        }
                        
                    } break;
                        
                    case "!":
                    {
                        calc.factorial(calc.rezultat());
                        
                    } break;
                        
                    case "sqrt":
                    {
                        calc.radical(calc.rezultat());
                        
                    } break;
                               
                    case "M+":
                    {
                        calc.adunM();        
                    
                    } break;
                        
                    case "M-":
                    {
                        calc.scadM();        
                    
                    } break;
                        
                    case "MS":
                    {
                        calc.stocare();        
                    
                    } break;
                    
                    case "MD":
                    {
                       calc.sterge();
                        
                    } break;
                        
                    case "M":
                    {
                       System.out.println(calc.memorie());
                        
                    } break;
                            
                    case "exit" : {} break;
                        
                    default :
                    {
                        if (numar(optiune)) { 
                            
                            calc.restoreRez();
                            calc.adunare(calc.rezultat(), Double.parseDouble(optiune));
                        }
                        
                        else if(optiune.equals("MC"))
                        {
                            calc.restoreRez();
                            calc.adunare(calc.rezultat(), calc.memorie());
                        }
                        
                        else{
                            
                        System.out.println("Va rugam introduceti spatii intre operatori si operanzi si numele operatorului corect");                        
                        calc.restoreRez();
                        
                        }
                     }                 
                }
                
                System.out.println("Rez = " + calc.rezultat());
            }
            
            catch (RemoteException ex){
                
                ex.printStackTrace();
            }
        }
            
            
              
    }
    
}

