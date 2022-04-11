import java.util.*;

import javax.sql.rowset.spi.SyncResolver;

import java.io.*;

public class CORE 
{
    private static ArrayList<PrintStream> theClientStreams = new ArrayList<PrintStream>();
    private static byte[] file;

    public static synchronized void changeClientPrintStreams(PrintStream ps, String doWhatWithIt)
    {
        if(doWhatWithIt.equals("add"))
        {
            System.out.println("Adding a client thread");
            CORE.theClientStreams.add(ps);
        }
        else if(doWhatWithIt.equals("remove"))
        {
            int hashcodeOfPrintStream = ps.hashCode();
            for(int i = 0; i < CORE.theClientStreams.size(); i++)
            {
                if(CORE.theClientStreams.get(i).hashCode() == hashcodeOfPrintStream)
                {
                    //CORE.theClientStreams.get(i).println("The client's hashcode on CORE " + CORE.theClientStreams.get(i).hashCode());
                    CORE.theClientStreams.get(i).println("Removing the current PrintStream... bye!");
                    CORE.theClientStreams.remove(i);
                    i--;
                }
            }
        }
    }

    public static void broadcastMessage(String message)
    {
        for (PrintStream ps : CORE.theClientStreams)
        {
            ps.println(message);
        }
    }

    public static void sendAFile(String filepath)
    {
        try 
        {
            FileInputStream fileInput = new FileInputStream(filepath);
            fileInput.read(file);
        }
        catch(Exception e)
        {
            System.err.println("There was an error: " + e.getMessage());
        }

    }

    public static byte[] recieveAFile()
    {
        return file;
    }


}   
