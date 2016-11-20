package pfc;
import pfc.Driver;
public class OutcomeObserver extends ConcreteObserver {
	

	public OutcomeObserver(Driver driver)
	{
		super(driver);
	}



	public void update ()
	{


		if(gameDriver.getState().equals("pfc.VerfifiedOutputState"))
		{
			observerState = gameDriver.getResult();
		}
	}

}