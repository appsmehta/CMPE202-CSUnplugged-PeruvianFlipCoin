import pfc.Driver;
public class ParityGuessObserver extends ConcreteObserver {
	

	public ParityGuessObserver(Driver driver)
	{
		super(driver);
	}



	public void update ()
	{


		if(gameDriver.getState().equals("pfc.VerfifiedOutputState"))
		{
			observerState = gameDriver.getGuessedParity();
		}
	}

}