package pfc;
import pfc.Driver;

public class InputObserver extends ConcreteObserver {
	

	public InputObserver(Driver driver)
	{
		super(driver);
	}

	public String observerState ="";

	public void update ()
	{


		if(gameDriver.getState().equals("pfc.NoInputState"))
		{
			observerState = gameDriver.getInputBits();
		}
	}

}