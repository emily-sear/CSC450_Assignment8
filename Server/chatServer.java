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
        ChatManagerThread manager = new ChatManagerThread();
        while(true)
        {
            System.out.println("Listening for Connection...");
            Socket client = s.accept(); //blocks until connection
    
           // ChatWorkerThread t = ChatWorkerThread.getInstance(client);
            System.out.println(s.hashCode());
           ChatWorkerThread t = new ChatWorkerThread(client, count, manager);
           System.out.println(t.hashCode());
            manager.addAThread(t);

            t.start(); // as soon as we start the thread, the server can start listening for another connection 
            count++;
        } 

    }
}
