import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

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
            CORE.changeClientPrintStreams(this.clientOutput, "add");
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
                CORE.broadcastMessage(name + " has left the Server.");
                CORE.changeClientPrintStreams(this.clientOutput, "remove");
                this.clientOutput.println("closing everything down");
                this.theClientSocket.close();
                this.clientInput.close();
                this.clientOutput.close();
            }
            else if(message.equals("/upload"))
            {
                this.clientOutput.println("Please enter the file path of the file you want to upload: ");
                String filePath = clientInput.nextLine();
                CORE.sendAFile(filePath);
                this.clientOutput.println("Your file has been successfully uploaded!");
            }
            else if(message.equals("/download"))
            {
                this.clientOutput.println("Please enter the file path of where you want this file to be saved: ");
                String filepath = clientInput.nextLine();
                File newFile = new File(filepath);
                boolean test = newFile.createNewFile();
                this.clientOutput.println(test);
                FileOutputStream dataInput = new FileOutputStream(newFile);
                byte[] file = CORE.recieveAFile();
                dataInput.write(file);

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