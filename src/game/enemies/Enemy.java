package game.enemies;

import base.Unit;
import game.CastleDefense;
import game.Startup;

import java.awt.image.BufferedImage;

/**
 * Methods For game.enemies.Enemy Units
 *
 * @author Cody Richter
 * @version 1.0
 */
public abstract class Enemy extends Unit
{
    protected int rewardMoney;

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
    public Enemy(int healthLevel, int damageLevel, int range, double speed, int money, BufferedImage sprite)
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
        currentRow = row;
        //Adds Unit To List Of Units On Gameboard
        Startup.b.addUnit(this);
        CastleDefense.enemies.add(this);

        //Sets X and Y Coordinates Of Spawned Unit
        int x = Startup.b.getWidth();
        int y;
        if (row == 1)
            y = CastleDefense.ROW1Y;
        else if (row == 2)
            y = CastleDefense.ROW2Y;
        else
            y = CastleDefense.ROW3Y;
        //bounds.add(x, y);
        pos.setLocation(x, y);
    }

    /*
    Returns Amount Of Money You Get For Killing a Unit
     */
    public int getRewardMoney()
    {
        return rewardMoney;
    }

    /*
    Kills An Enemy and Removes It From The Board
    */
    public void kill()
    {
        if(Startup.VERBOSE) System.out.println(this + " DYING");
        //Startup.b.removeUnit(this);//CAUSES CRASH WHEN ELEMENTS ARE BEING MODIFIED
        CastleDefense.addMoney(rewardMoney);
        if (getRewardMoney() > 0)
            Startup.b.sendNotification("Recieved $" + getRewardMoney() + " for killing " + this.toString());

        isDead = true;
        pos.setLocation(5,5);
        Startup.menu.repaint();
    }

}
