//import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
package pfc;
/**
 * Write a description of class Driver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver
{
    /**
     * Act - do whatever the Driver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //CaptainA team1;
    //CaptainB team2;
    GameState state = new GameState();
    Circuit c = new Circuit();
    
    private static Driver theDriver;

    boolean[] boolEncoded = new boolean[6];
    boolean[] boolInputCaptainA = new boolean[6];
    int inputParity, guessedParity;
    
    NoInputState noInputState;
    EncodedInputState encodedInputState;
    VerifiedOutputState verifiedOutputState;

    public static Driver getInstance()
    {
        if(theDriver == null)
        {
            theDriver = new Driver();
            return theDriver;
        }
        else
        {
            return theDriver;
        }
    }


    public Driver()
    {
        
        //team1 = new CaptainA(2);
        //team2 = new CaptainB(4);
        
        noInputState = new NoInputState();
        encodedInputState = new EncodedInputState();
        verifiedOutputState = new VerifiedOutputState();

        state = noInputState;
    }
    
    public String encodedInput(String input)
    {
        //this.boolEncoded = boolEncoded;


        char [] inputbits = input.toCharArray();
        int i=0;
        for(char a : inputbits)
        {
            if(a=='1')
            boolInputCaptainA[i]=true;
            else
            boolInputCaptainA[i]=false;
          i++;
        }
            //this.boolInputCaptainA = boolInputCaptainA;
            System.out.println("Input: "+boolInputCaptainA[0]+""+boolInputCaptainA[1]+""+boolInputCaptainA[2]+""+boolInputCaptainA[3]+""+boolInputCaptainA[4]+""+boolInputCaptainA[5]);
            
            boolEncoded = c.randomSelection(boolInputCaptainA);
            
            System.out.println("Encoded: "+boolEncoded[0]+""+boolEncoded[1]+""+boolEncoded[2]+""+boolEncoded[3]+""+boolEncoded[4]+""+boolEncoded[5]);
            
            inputParity = findParity();
            
            state = encodedInputState;
            return new String("EncodedInputState");
    }

    public String guessParity(int guessedParity)
    {
        String output = "";
        this.guessedParity = guessedParity;
            if(guessedParity == inputParity)
            {
                output = "Captain B won";
                System.out.println("Captain B won");
            }
            else
            {
                output = "Captain A won";
                System.out.println("Captain A won");
            }
            
            state = verifiedOutputState;
            output = output + ",VerifiedOutputState";
            return output;
    }
/*
    public void act() 
    {
        if(state.getClass().getName() == "KickOffState")
        {
            boolInputCaptainA = team1.encodeInput();
            System.out.println("Input: "+boolInputCaptainA[0]+""+boolInputCaptainA[1]+""+boolInputCaptainA[2]+""+boolInputCaptainA[3]+""+boolInputCaptainA[4]+""+boolInputCaptainA[5]);
            
            boolEncoded = c.randomSelection(boolInputCaptainA);
            
            System.out.println("Encoded: "+boolEncoded[0]+""+boolEncoded[1]+""+boolEncoded[2]+""+boolEncoded[3]+""+boolEncoded[4]+""+boolEncoded[5]);
            
            inputParity = findParity();
            
            state = new EncodedInputState();
        }
        
        if(state.getClass().getName() == "EncodedInputState")
        {
            guessedParity = team2.getParity();
            
            if(guessedParity == inputParity)
            {
                System.out.println("Captain B won");
            }
            else
            {
                System.out.println("Captain A won");
            }
            
            state = new VerifiedOutputState();
        }
        
        
        // Add your action code here.
    }   
*/
    public int findParity()
    {
        int i=0;
        int bitCounter = 0;
        for(i=0; i<6; i++)
        {
            if(boolInputCaptainA[i]==true)
            {
                bitCounter++;
            }
        }
        if(bitCounter%2 == 0)
        return 1;
        else
        return 0;
    }
}
