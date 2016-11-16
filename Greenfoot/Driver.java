import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import org.restlet.resource.ClientResource;
//import org.restlet.representation.Representation ;
import org.restlet.representation.* ;
import org.restlet.ext.json.*;
import org.json.*;
import java.util.ArrayList;

/**
 * Write a description of class Driver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver extends Actor
{
    /**
     * Act - do whatever the Driver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private final String service_url = "http://peruvian-2.222f8ff7.svc.dockerapp.io:80/Peru";
    
    CaptainA team1;
    CaptainB team2;
    //GameState state = new GameState();
    String state;
    Circuit c = new Circuit();
    ClientResource peruvianClient;
    boolean[] boolEncoded = new boolean[6];
    boolean[] boolInputCaptainA = new boolean[6];
    int inputParity, guessedParity;
    int playerNumber=9;
    Mobile m;
    
    
    public Driver()
    {
        
        team1 = new CaptainA(2);
        team2 = new CaptainB(4);
        peruvianClient = new ClientResource( service_url ); 
        //state = new KickOffState();
        JSONObject json;
        Representation result = peruvianClient.get();
        System.out.println("In constructor");
        
       
        //World world = getWorld();
      
   
        //getWorld().addObject(m, 200, 200);
        try
        {
            json = new JSONObject(result.getText());
            state = json.getString("result");
            //state = json.getString("result");
            //Mobile m = new Mobile()
            //getWorld().addObject(m, 200, 200);
        }
        catch(Exception e)
        {
        }
        
        
    }
    
    public void act() 
    {
        //state = peruvian
        Representation result = peruvianClient.get();
        try
        {
            JSONObject json = new JSONObject(result.getText());
            state = json.getString("result");
        }
        catch(Exception e)
        {
        }
        
        ArrayList<Mobile> arr = (ArrayList<Mobile>)getWorld().getObjectsAt(466,46,Mobile.class);
        
       if(Greenfoot.mouseClicked(arr.get(0)))
       {
           createGame();
        }
        
        ArrayList<Mobile> arrr = (ArrayList<Mobile>)getWorld().getObjectsAt(140,356,Mobile.class);
        
        if(Greenfoot.mouseClicked(arrr.get(0)))
        {
            joinGame();
        }
        
        
        if(state.equals("pfc.NoInputState") && playerNumber==1){
            getInputString();
        }
        
        if(state.equals("pfc.EncodedInputState") && playerNumber==2){
            getParity();
        }
        
        
        
        /*
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
        
        */
        // Add your action code here.
    }   
    
    public void createGame()
    {
        if(state.equals("pfc.WaitingState"))
        {
            JSONObject sendRequest = new JSONObject();
            sendRequest.put("function", "CreateGame");
            //response.put( "result", verified);
            //response.put("state",gameState);
            //JsonRepresentation sendRequest = new JsonRepresentation();
            playerNumber = 1;
            Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
            try
            {
                JSONObject keyJson = new JSONObject(keyResult.getText());
                String gameKey = keyJson.getString("result");
                state = keyJson.getString("state");
                System.out.println("Game Key"+gameKey);
                System.out.println("Game State"+state);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            //Representation result = peruvianClient.get() ;
        }
    }
    
    public void joinGame()
    {
       String input = Greenfoot.ask("Enter 4 digit Game Key to connect");
       
       JSONObject sendRequest = new JSONObject();
            sendRequest.put("function", "JoinGame");
            sendRequest.put("gameKey", input);
            //response.put( "result", verified);
            //response.put("state",gameState);
            //JsonRepresentation sendRequest = new JsonRepresentation();
            playerNumber = 2;
            Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
            try
            {
                JSONObject keyJson = new JSONObject(keyResult.getText());
                String verifiedKey = keyJson.getString("result");
                state = keyJson.getString("state");
                System.out.println("VerifiedKey:"+verifiedKey);
                System.out.println("Game State"+state);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
    
    
    public void getInputString(){
        
       String input = Greenfoot.ask("Please enter six digit input string");
       
       JSONObject sendRequest = new JSONObject();
            sendRequest.put("function", "EncodeBits");
            sendRequest.put("inputBits", input);
            //response.put( "result", verified);
            //response.put("state",gameState);
            //JsonRepresentation sendRequest = new JsonRepresentation();
           
            Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
            try
            {
                JSONObject keyJson = new JSONObject(keyResult.getText());
                String verifiedKey = keyJson.getString("result");
                state = keyJson.getString("state");
                System.out.println("VerifiedKey:"+verifiedKey);
                System.out.println("Game State"+state);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        
    }
    
    
    
    public void getParity()
    {
       String input = Greenfoot.ask("Please Guess the Parity");
       
       JSONObject sendRequest = new JSONObject();
            sendRequest.put("function", "GuessParity");
            sendRequest.put("inputParity", input);
            Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
            try
            {
                JSONObject keyJson = new JSONObject(keyResult.getText());
                String verifiedKey = keyJson.getString("result");
                state = keyJson.getString("state");
                System.out.println("VerifiedKey:"+verifiedKey);
                System.out.println("Game State"+state);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
}
