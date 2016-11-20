import pfc.Driver;

public class ConcreteObserver implements PeruvianObserver {
 
	protected Driver gameDriver;
    protected String observerState;

    public ConcreteObserver( Driver driver )
    {
        this.gameDriver = driver ;
    }
    
	public void update() {
	    // do nothing
	}

    public void showState()
    {
        System.out.println( "Observer: " + this.getClass().getName() + " = " + observerState );
    }
	 
}
 