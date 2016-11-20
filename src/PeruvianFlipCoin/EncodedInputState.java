package pfc;
import java.util.concurrent.ThreadLocalRandom;

public class EncodedInputState extends GameState 
{
   
    public EncodedInputState(Driver gameDriver)
    {
        super(gameDriver);
    }

   
 
    public String toString()
    {
        return "Captain has encoded a String";
    }

}
