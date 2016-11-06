import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mobile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mobile extends Actor
{
    static GreenfootImage[] images={ new GreenfootImage("mob1.png"),
                                     new GreenfootImage("mob2.png")};
    int mobile;
    public Mobile(int mobNum)
    {
        mobile= mobNum;
        setImage(images[mobNum]);
    }
    /**
     * Act - do whatever the Mobile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
