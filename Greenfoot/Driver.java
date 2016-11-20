import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import org.restlet.resource.ClientResource;
//import org.restlet.representation.Representation ;
import org.restlet.representation.* ;
import org.restlet.ext.json.*;
import org.json.*;
import java.util.ArrayList;
import javax.swing.*;

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
    
    private final String service_url = "http://peruvian-3c74081a.d9c9ced2.svc.dockerapp.io:80/Peru";
    
    CaptainA team1;
    CaptainB team2;
    //GameState state = new GameState();
    String state;
    int animationFlag = 0;
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
        
        ArrayList<Mobile> arr = (ArrayList<Mobile>)getWorld().getObjectsAt(498,219,Mobile.class);
        if(arr.size()!=0)
        {
            if(Greenfoot.mouseClicked(arr.get(0)))
            {
                createGame();
            }
        }
        
        ArrayList<Mobile> arrr = (ArrayList<Mobile>)getWorld().getObjectsAt(500,279,Mobile.class);
        if(arrr.size()!=0)
        {
            if(Greenfoot.mouseClicked(arrr.get(0)))
            {
                joinGame();
            }
        }
        
        if(state.equals("pfc.NoInputState"))
        {
            
            if(animationFlag == 0)
            {
             prepare();
           //  initialAnimation();
             animationFlag = 1;             
            }
        }
        
        
        if(state.equals("pfc.NoInputState") && playerNumber==1 && animationFlag == 1){
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
    
    private void prepare()
    {
        ArrayList<Mobile> arr = (ArrayList<Mobile>)getWorld().getObjectsAt(498,219,Mobile.class);
        getWorld().removeObject(arr.get(0));
        ArrayList<Mobile> arrr = (ArrayList<Mobile>)getWorld().getObjectsAt(500,279,Mobile.class);
        getWorld().removeObject(arrr.get(0));
        GreenfootImage images =  new GreenfootImage("world1.png");
        getWorld().setBackground(images);
        Player player1 = new Player(0);
        getWorld().addObject(player1, 372, 331);
        Player player2 = new Player(1);
        getWorld().addObject(player2, 561, 331);
        Player player3 = new Player(2);
        getWorld().addObject(player3, 237, 64);
        Player player4 = new Player(3);
        getWorld().addObject(player4, 44, 64);
        CaptainA capA = new CaptainA(5);
        getWorld().addObject(capA, 137, 205);
        CaptainB capB = new CaptainB(4);
        getWorld().addObject(capB, 461, 205);
        Greenfoot.delay(50);
        capA.setLocation(255,205);
        capB.setLocation(345,200);
        Greenfoot.delay(50);
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
             //   System.out.println("Game Key"+gameKey);
             
                JFrame frame = new JFrame("circuitInput");
                JOptionPane.showMessageDialog(frame, "Your Game Key: "+gameKey);
             
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
       //String input = Greenfoot.ask("Enter 4 digit Game Key to connect");
       
       JFrame frame = new JFrame("circuitInput");
       String input = JOptionPane.showInputDialog(frame, "Enter 4 digit Game Key to connect");
       
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
                
                if(verifiedKey.equals("false"))
                {
                    JFrame frame1 = new JFrame("circuitInput");
                    JOptionPane.showMessageDialog(frame1,"Incorrect Gamekey, Please reenter","Warning",JOptionPane.WARNING_MESSAGE);
                    System.out.println("VerifiedKey:"+verifiedKey);
                    joinGame();
                }
                
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
        
       //String input = Greenfoot.ask("Please enter six digit input string");
       JFrame frame = new JFrame("circuitInput");
       String input = JOptionPane.showInputDialog(frame, "Enter 6 binary bits to be encoded:");
       
       if(input == null || input.isEmpty() || !input.matches("[0-1]*") || input.length() != 6)
        {
            input = "";
            JFrame frame1 = new JFrame("circuitInput");
            JOptionPane.showMessageDialog(frame1,"Invalid input string, Please reenter","Warning",JOptionPane.WARNING_MESSAGE);
            getInputString();
        }
       
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
                //state = keyJson.getString("state");
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
      // String input = Greenfoot.ask("Please Guess the Parity");
      JFrame frame = new JFrame("circuitInput");
      String input = JOptionPane.showInputDialog(frame, "Enter Parity:");
      
      if(input == null || input.isEmpty() || !input.matches("[0-1]*") || input.length() != 1)
        {
            input = "";
            JFrame frame1 = new JFrame("circuitInput");
            JOptionPane.showMessageDialog(frame1,"Invalid parity, please reenter","Warning",JOptionPane.WARNING_MESSAGE);
            getParity();
        }
       
       JSONObject sendRequest = new JSONObject();
            sendRequest.put("function", "GuessParity");
            sendRequest.put("inputParity", input);
            Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
            try
            {
                JSONObject keyJson = new JSONObject(keyResult.getText());
                String verifiedKey = keyJson.getString("result");
                //state = keyJson.getString("state");
                System.out.println("VerifiedKey:"+verifiedKey);
                
                showResult(verifiedKey); 
                System.out.println("Game State"+state);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
    
    
    public void showResult(String verifiedKey)
    {
        String[] result = verifiedKey.split(",", 2);
        System.out.println("VerifiedKey:"+result[0]);
        JFrame frame1 = new JFrame("circuitInput");
        JOptionPane.showMessageDialog(frame1, ""+result[0]);
    }
    
    
    public void initialAnimation()
    {
                World world = getWorld();
                
                Greenfoot.delay(50);
                Mobile mobile1 = new Mobile(1);
                world.addObject(mobile1, 315, 150);
               
                Mobile mobile2 = new Mobile(1);
                world.addObject(mobile2, 282, 150);
                Greenfoot.delay(20);
                //world.showText("Hey Rooney!!", 225, 100);
                Greenfoot.delay(50);
                
                //world.showText("Hey Chris!!", 370, 100);
                Message message1 = new Message(0);
                world.addObject(message1, 385, 80);
                Greenfoot.delay(150);
                world.removeObject(message1);
                
                
                Message message2 = new Message(2);
                world.addObject(message2, 210, 80);
                Greenfoot.delay(150);
                world.removeObject(message2);
                
                Message message3 = new Message(3);
                world.addObject(message3, 385, 80);
                Greenfoot.delay(150);
                world.removeObject(message3);
                
                Message message4 = new Message(4);
                world.addObject(message4, 210, 80);
                Greenfoot.delay(150);
                world.removeObject(message4);
                
                Message message5 = new Message(5);
                world.addObject(message5, 385, 80);
                Greenfoot.delay(150);
                world.removeObject(message5);
                
                Message message6 = new Message(6);
                world.addObject(message6, 210, 80);
                Greenfoot.delay(200);
                world.removeObject(message6);
                
                Message message7 = new Message(7);
                world.addObject(message7, 385, 80);
                Greenfoot.delay(200);
                world.removeObject(message7);
                
                Message message8 = new Message(8);
                world.addObject(message8, 210, 80);
                Greenfoot.delay(200);
                world.removeObject(message8);
                
                Message message9 = new Message(9);
                world.addObject(message9, 385, 80);
                Greenfoot.delay(200);
                world.removeObject(message9);
                
                Message message10 = new Message(10);
                world.addObject(message10, 210, 80);
                Greenfoot.delay(200);
                world.removeObject(message10);
                
                Message message11 = new Message(11);
                world.addObject(message11, 385, 80);
                Greenfoot.delay(200);
                world.removeObject(message11);
                
                
                Message message12 = new Message(12);
                world.addObject(message12, 210, 80);
                Greenfoot.delay(200);
                world.removeObject(message12);
                
                Message message13 = new Message(13);
                world.addObject(message13, 385, 80);
                
                Circuit circuit = new Circuit();
                world.addObject(circuit, 291, 282);
                Greenfoot.delay(200);
                world.removeObject(message13);
                world.removeObject(circuit);
                
                
                Message message14 = new Message(14);
                world.addObject(message14, 385, 80);
                Greenfoot.delay(200);
                world.removeObject(message14);
                
                Message message15 = new Message(15);
                world.addObject(message15, 385, 80);
                Greenfoot.delay(200);
                world.removeObject(message15);
                
                Message message16 = new Message(16);
                world.addObject(message16, 210, 80);
                Greenfoot.delay(200);
                world.removeObject(message16);
             
                Greenfoot.delay(50);
    }
    
}
