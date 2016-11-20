package pfc;

public class NoInputState extends GameState 
{
    
    public NoInputState(Driver gameDriver)
    {
        super(gameDriver);
    }

    public void setState(){
        gameDriver.setState(gameDriver.getEncodedInputState());
    }
           
    public String toString()
    {
        return "No Input State";
    }
}
