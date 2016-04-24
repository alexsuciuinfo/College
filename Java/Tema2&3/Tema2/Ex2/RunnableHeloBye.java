public class RunnableHeloBye {

        public static void main(String[] args) {
            
            Thread hello = new Thread(new Hello());
            Thread goodbye = new Thread(new Goodbye());
            hello.start();
            goodbye.start();
        }
        
    }

class Hello implements Runnable  {

    public void run()
     {
      int  pause;
      for (int i=0; i<10; i++)
        {
         try
           {
            System.out.println("Hello!");
            pause = (int)(Math.random() * 2000);
            Thread.sleep(pause);
           }
         catch (InterruptedException interruptEx)
            {
            	System.out.println(interruptEx);
            }
      	}
   }
}
        

class Goodbye implements Runnable {

     public void run()
     {
      int  pause;
      for (int i=0; i<10; i++)
        {
         try
           {
            System.out.println("Goodbye!");
            pause = (int)(Math.random() * 2000);
            Thread.sleep(pause);
           }
         catch (InterruptedException interruptEx)
            {
            	System.out.println(interruptEx);
            }
      	}
   }
}