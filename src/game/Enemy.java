package game;

import base.Unit;

import java.awt.image.BufferedImage;

/**
 * Methods For game.Enemy Units
 *
 * @author Cody Richter
 * @version 1.0
 */
public abstract class Enemy extends Unit
{
    private int rewardMoney;

    /**
     * Makes new enemy unit
     */
    public Enemy()
    {
        super(1,1,1,-1,null,null);
        rewardMoney = 10;
        isEnemy = true;
    }

    /**
     * Makes new enemy unit with given attributes
     */
    public Enemy(int healthLevel, int damageLevel, int range, int speed, int money, BufferedImage sprite)
    {
        super(healthLevel, damageLevel, range, (speed * -1),sprite,null);
        rewardMoney = money;
        isEnemy = true;
    }

    /*
    Moves Enemy Unit From Right Side Of Screen To The Left Relative To Movement Speed
     */
    public void move()
    {
        pos.setLocation(pos.getX() + moveSpeed ,  pos.getY());
    }

    public void spawn(int row){
        if (row > 3 || row < 1) return; //Will Ensure Unit Is Spawned In Correct Row

        //Adds Unit To List Of Units On Gameboard
        Main.b.addUnit(this);
        CastleDefense.enemies.add(this);

        //Sets X and Y Coordinates Of Spawned Unit
        int x = Main.b.getWidth();
        int y;
        if (row == 1)
            y = CastleDefense.ROW1X;
        else if (row == 2)
            y = CastleDefense.ROW2X;
        else
            y = CastleDefense.ROW3X;
        //bounds.add(x, y);
        pos.setLocation(x, y);
    }

    /*
    Kills An Enemy and Removes It From The Board
    */
    public void kill()
    {
        if(Main.VERBOSE) System.out.println(this + " DYING");
        Main.b.removeUnit(this);
        CastleDefense.addMoney(rewardMoney);
        isDead = true;
        pos.setLocation(5,5);
    }

}
