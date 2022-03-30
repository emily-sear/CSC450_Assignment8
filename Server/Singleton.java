public class Singleton 
{
    private static Singleton instance = null;

    public String s;

    private Singleton()
    {
        this.s = "Hello I am a string part of the Singleton class";
    }

    public static Singleton getInstance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }
}
