import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatWorkerThread extends Thread 
{
    private Socket theClientSocket;
    private PrintStream clientOutput;
    private Scanner clientInput;

    //homework --> get hash code

    public ChatWorkerThread(Socket theClientSocket)
    {
        try 
        {
            System.out.println("Connection Established...");
            this.theClientSocket = theClientSocket;
            this.clientOutput = new PrintStream(this.theClientSocket.getOutputStream());
            CORE.addClientPrintStream(this.clientOutput);
            this.clientInput = new Scanner(this.theClientSocket.getInputStream());
        } 
        catch (Exception e) 
        {
            System.err.println("Bad things happened in thread!!!!!");
            e.printStackTrace();
        }
        
    }

    public void run()
    {
        try
        {
                    //this is what the thread does
        this.clientOutput.println("What is your name?");
        String name = clientInput.nextLine();
        CORE.broadcastMessage(name + " has joined!");

        String message;
        while(true)
        {
            message = clientInput.nextLine();
            if(message.equals("/quit"))
            {
                CORE.removePrintStream(this.clientOutput.hashCode());
                this.clientOutput.println("closing everything down");
                this.theClientSocket.close();
                this.clientInput.close();
                this.clientOutput.close();
            }
            CORE.broadcastMessage(message);
        }
        }
        catch(Exception e)
        {
            System.out.println("There was an error:\n" + e.getMessage());
        }

    }
}