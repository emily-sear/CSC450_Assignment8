import java.util.*;
import java.net.*;

public class ChatManager 
{
    private static ChatManager instance = null;
    public ArrayList<String> theMessages;

    private ChatManager()
    {
        this.theMessages = new ArrayList<String>();
    }

    public static ChatManager ChatManager()
    {
        if (instance == null)
        {
            instance = new ChatManager();
        }
        return instance;
    }

}
