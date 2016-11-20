package pfc;

public class VerifiedOutputState extends GameState 
{
    
    public VerifiedOutputState(Driver gameDriver)
    {
        super(gameDriver);
    }

    public void setState(){
        //gameDriver.setState(gameDriver.get());
    }

    public String toString()
    {
        return "VerifiedOutputState";
    }
    
    
    
}
