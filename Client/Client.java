import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    public static void main(String[] args) throws Exception
    {
        Socket s = new Socket("localhost", 2222); 
        Scanner clientInput = new Scanner(s.getInputStream());
        
        String question = clientInput.nextLine();
        System.out.println(question);
        Scanner localInput = new Scanner(System.in);
        PrintStream clientOutput = new PrintStream(s.getOutputStream());

        
        Thread lt = new Thread()
        { // Closure... locally overriding a class 
            public void run()
            {
                try
                {
                    String line;
                    while(true)
                    {
                        line = clientInput.nextLine();
                        if(line.equals("closing everything down"))
                        {
                            s.close();
                            clientInput.close();
                            clientOutput.close();
                            System.out.println("Bye!");
                        }
                        System.out.println(line);
                    }
                }
                
                catch(Exception e)
                {
                    System.err.println("There was an error:\n" + e.getMessage());
                }
            }
        };

        lt.start();
        String message;
        while(true)
        {
            message = localInput.nextLine();
            clientOutput.println(message);
            if(message.equals("/quit"))
            {
                break;
            }

        } 

    }
}