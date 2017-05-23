package game;

import base.Unit;
import game.friendly.Catapult;
import game.friendly.Infantry;
import game.friendly.Knight;
import game.friendly.Militia;

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
    public Friendly(int healthLevel, int damageLevel, int range, int speed)
    {
        super(healthLevel, damageLevel, range, (speed * -1),null ,null);
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
        currentRow = row;
        if (row > 3 || row < 1) return; //Will Ensure Unit Is Spawned In Correct Row

        //Adds Unit To List Of Units On Gameboard
        Main.b.addUnit(this);
        CastleDefense.addFriendly(this);

        //Sets X and Y Coordinates Of Spawned Unit
        int x = 0;
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
    Kills A Friendly and Removes It From The Board
     */
    public void kill()
    {
        if(Main.VERBOSE) System.out.println(this + " DYING");
        //Main.b.removeUnit(this); //CAUSES CRASH WHEN ELEMENTS ARE BEING MODIFIED
        isDead = true;
        pos.setLocation(5,5);
    }

    public static int getUnitCost(Friendly f)
    {
        if (f instanceof Militia)
            return Militia.COST;
        if (f instanceof Infantry)
            return Infantry.COST;
        if (f instanceof Knight)
            return Knight.COST;
        if (f instanceof Catapult)
            return Catapult.COST;
        return -1;
    }

}
