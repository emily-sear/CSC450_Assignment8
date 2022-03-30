import java.util.*;

public class ChatManagerThread 
{
    private ArrayList<ChatWorkerThread> theWorkers;
    private ArrayList<String> theMessages;

    public ChatManagerThread()
    {
        this.theWorkers = new ArrayList<ChatWorkerThread>();
        this.theMessages = new ArrayList<String>();
    }

    public void addAThread(ChatWorkerThread newThread)
    {
        this.theWorkers.add(newThread);
    }

    public void someoneSaidSomething(int workerThreadID, String message)
    {
        for(int i = 0; i < theWorkers.size() - 1; i++)
        {
            if(workerThreadID != theWorkers.get(i).getWorkerThreadID())
            {
                this.theWorkers.get(i).getClientOutput().println("Thread #"+ workerThreadID + " said: " + message);
                System.out.println("Made it "+ i);
            }

        }
    }
}
