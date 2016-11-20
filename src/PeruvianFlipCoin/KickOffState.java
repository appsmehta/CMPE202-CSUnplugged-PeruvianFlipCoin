package pfc;

public class KickOffState extends GameState 
{
    
    public KickOffState(Driver gameDriver)
    {
        super(gameDriver);
    }

    public void setState(){
        gameDriver.setState(gameDriver.getNoInputState());
    }
    
       
    public String toString()
    {
        return "KickOffState";
    }
    
}
