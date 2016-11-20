package pfc;
import java.util.concurrent.ThreadLocalRandom;

public class EncodedInputState extends GameState 
{
   
    public EncodedInputState(Driver gameDriver)
    {
        super(gameDriver);
    }

   
    public void setState(){
        gameDriver.setState(gameDriver.getVerifiedOutputState());
    }
 
    public String toString()
    {
        return "Captain has encoded a String";
    }

}
