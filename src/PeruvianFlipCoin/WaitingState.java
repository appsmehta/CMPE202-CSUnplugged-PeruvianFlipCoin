package pfc;

public class WaitingState extends GameState 
{
    
   
    public WaitingState(Driver gameDriver)
    {
        super(gameDriver);
    }

    public void setState(){
        gameDriver.setState(gameDriver.getKickOffState());
    }   
    
    public String toString()
    {
        return "WaitingState";
    }
    
}
