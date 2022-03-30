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

        while(true)
        {
            String message = input.nextLine();
            System.out.println(message);
    
            String name = userInput.nextLine();
            output.println(name);
    
        }

    }
}
