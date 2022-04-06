import java.util.*;
import java.io.*;

public class CORE 
{
    private static ArrayList<PrintStream> theClientStreams = new ArrayList<PrintStream>();

    public static synchronized void addClientPrintStream(PrintStream ps)
    {
        System.out.println("Adding a client thread");
        CORE.theClientStreams.add(ps);
    }

    public static void broadcastMessage(String message)
    {
        for (PrintStream ps : CORE.theClientStreams)
        {
            ps.println(message);
        }
    }

    public static void removePrintStream(int hashcodeOfPrintStream)
    {
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
