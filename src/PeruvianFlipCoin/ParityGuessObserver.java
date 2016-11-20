public class ParityGuessObserver extends ConcreteObserver {
	

	public ParityGuessObserver(Driver driver)
	{
		super(driver);
	}



	public void update ()
	{


		if(gameDriver.getState().equals("pfc.VerfifiedOutputState"))
		{
			observerState = driver.getGuessedParity();
		}
	}

}