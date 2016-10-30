import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PeruvianFlipCoinWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public PeruvianFlipCoinWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 

        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

        Player player1 = new Player(0);
        addObject(player1, 372, 331);
        Player player2 = new Player(1);
        addObject(player2, 561, 331);
        Player player3 = new Player(2);
        addObject(player3, 237, 64);
        Player player4 = new Player(3);
        addObject(player4, 44, 64);
        CaptainA capA = new CaptainA(5);
        addObject(capA, 137, 205);
        CaptainB capB = new CaptainB(4);
        addObject(capB, 461, 205);
    }
}
