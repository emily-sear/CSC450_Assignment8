import java.io.*;
import java.net.*;
import java.util.*;

public class chatServer
{
    public static void main(String[] args) throws Exception
    {
        // only to create the Server once 
        ServerSocket s = new ServerSocket(2222);
        int count = 0;
    
        while(true)
        {
            System.out.println("Listening for Connection...");
            Socket client = s.accept(); //blocks until connection
            System.out.println("Connection made");
    
           // ChatWorkerThread t = ChatWorkerThread.getInstance(client);
        // as soon as we start the thread, the server can start listening for another connection 
        } 

    } 
}

