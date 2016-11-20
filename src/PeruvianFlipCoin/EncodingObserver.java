public class EncodingObserver extends ConcreteObserver
{
	public EncodingObserver(Driver driver){

		super(driver);
	}


	public void update ()
	{


		if(gameDriver.getState().equals("pfc.NoInputState"))
		{
			observerState = driver.getEncodedBits();
		}
	}
}