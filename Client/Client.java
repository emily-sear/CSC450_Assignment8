import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client 
{
    public static void main(String[] args) throws Exception
    {
        Socket client = new Socket("localhost", 2222); // blocks until connection 

        Scanner input = new Scanner(client.getInputStream());
        Scanner userInput = new Scanner(System.in);
        PrintStream output = new PrintStream(client.getOutputStream());
        ChatManager manager = ChatManager.ChatManager();
        while(true)
        {
            for(int i = 0; i < manager.theMessages.size(); i++)
            {
                System.out.println(manager.theMessages.get(i));
            }
            String name = userInput.nextLine();
            output.println(name);
            manager.theMessages.add(name);
    

    
        }

    }
}
