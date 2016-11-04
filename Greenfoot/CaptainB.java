import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
            
           // setImage(images[pNum]);
        }  
    /**
     * Act - do whatever the CaptainB wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        setLocation(345,200);
        boolean[] boolinput = encodeInput();
    }    
    
    public boolean[] encodeInput(){
        String input="";
        boolean[] boolinput = new boolean[6];
        do{
                
             input = Greenfoot.ask("Enter 6 binary bits to be encoded:");
            
        }
        while(input.length()!=6);
        
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
    
    
}
