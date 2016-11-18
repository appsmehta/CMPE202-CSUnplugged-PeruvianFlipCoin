import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;

/**
 * Write a description of class CaptainB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaptainB extends Player
{
    
    public CaptainB(int pNum)
        {
            super(pNum);
             Greenfoot.delay(50);
             setLocation(345,200);
             Greenfoot.delay(50);
        }  
    /**
     * Act - do whatever the CaptainB wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        setLocation(345,200);
        //boolean[] boolinput = encodeInput();
    }    
    
    public boolean[] encodeInput(){
        String input="";
        boolean[] boolinput = new boolean[6];
                
        input = Greenfoot.ask("Enter 6 binary bits to be encoded:");
        
        System.out.println(input);
        char [] inputbits = input.toCharArray();
        int i=0;
        for(char a : inputbits)
        {
            if(a=='1')
            boolinput[i]=true;
            else
            boolinput[i]=false;
          i++;
        }
        
        return boolinput;
    }
    
        public int getParity()
    {
        //String input = Greenfoot.ask("Enter Parity:");
        
        JFrame frame = new JFrame("circuitInput");
        String input = JOptionPane.showInputDialog(frame, "Enter Parity:");
        System.out.println("Parity: "+input);
        if(input == "0")
        return 0;
        else
        return 1;
    }
    
}
