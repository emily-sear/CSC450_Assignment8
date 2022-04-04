import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ChatWorkerThread extends Thread
{
    private Socket theClientSocket;
    private PrintStream clientOutput;
    private Scanner clientInput;
    private int workerThreadID;
   // private static ChatWorkerThread instance = null;

    public ChatWorkerThread(Socket theClientSocket, int workerThreadID)
    {
        try 
        {
            System.out.println("Connection Established...");
            this.theClientSocket = theClientSocket;
            this.workerThreadID = workerThreadID;
            this.clientOutput = new PrintStream(this.theClientSocket.getOutputStream());
            this.clientInput = new Scanner(this.theClientSocket.getInputStream());
        }
        catch (Exception e)
        {
            System.err.println("Bad things happened in thread");
            e.printStackTrace(); // gives you the full error message
        }

    }

    /**public static ChatWorkerThread getInstance(Socket theClientSocket, int workerThreadID)
    {
        if(instance == null)
        {
            instance = new ChatWorkerThread(theClientSocket, workerThreadID);
        }
        return instance;
    } **/

    public PrintStream getClientOutput()
    {
        return this.clientOutput;
    }

    public int getWorkerThreadID()
    {
        return this.workerThreadID;
    }
    public void run()
    {
        // this is what the thread does  
        clientOutput.println("Say hi!");
        if(this.workerThreadID == 0)
        {
            while(true)
            {
                String message = clientInput.nextLine();
                try{
                    wait(5000);
                }
                catch (Exception e)
                {
                    System.out.println("Something went wrong");
                }
            }
    
        }
        else
        {
            while(true)
            {
                try{
                    wait(5000);
                }
                catch (Exception e)
                {
                    System.out.println("Something went wrong");
                }
                String message = clientInput.nextLine();

            }
    
        }

        //System.out.println("read: " + message);

    }
}
