package game;

import base.Unit;

import java.awt.image.BufferedImage;

/**
 * Methods For game.Friendly Units
 * 
 * @author Cody Richter
 * @version 1.0
 */
public abstract class Friendly extends Unit
{

    /**
     * Makes New game.Friendly base.Unit
     */
    public Friendly()
    {
        super(1,1,1,1,null,null);
        isEnemy = false;
    }

    /**
     * Makes new friendly unit with given attributes
     */
    public Friendly(int healthLevel, int damageLevel, int range, int speed, BufferedImage sprite)
    {
        super(healthLevel, damageLevel, range, (speed * -1),sprite,null);
        isEnemy = false;
    }

    /*
    Moves Enemy Unit From Left Side Of Screen To The Right Relative To Movement Speed
    */
    public void move()
    {
        pos.setLocation(pos.getX() - moveSpeed ,  pos.getY());
    }


    /*
    Spawns Unit Into Game Onto Given Row
     */
    public void spawn(int row){
        if (row > 3 || row < 1) return; //Will Ensure Unit Is Spawned In Correct Row

        //Adds Unit To List Of Units On Gameboard
        Main.b.addUnit(this);

        //Sets X and Y Coordinates Of Spawned Unit
        int x = 0;
        int y;
        if (row == 1)
            y = (Main.b.getHeight()/8);
        else if (row == 2)
            y = 3*(Main.b.getHeight()/8);
        else
            y = 5*(Main.b.getHeight()/8);
        //bounds.add(x, y);
        pos.setLocation(x, y);
    }

    /*
    Kills A Friendly and Removes It From The Board
     */
    public void kill()
    {
        CastleDefense.friendlies.remove(this);
    }

}
