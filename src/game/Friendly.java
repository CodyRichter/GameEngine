package game;

import base.Unit;
import game.friendly.Catapult;
import game.friendly.Infantry;
import game.friendly.Knight;
import game.friendly.Militia;

/**
 * Methods For Friendly Units
 *
 * @author Cody Richter
 * @version 1.0
 */
public abstract class Friendly extends Unit
{

    /**
     * Creates a new Friendly Unit with default attributes.
      */
    public Friendly()
    {
        super(1,1,1,1,null,null);
        isEnemy = false;
    }

    /**
     * Creates a new Friendly unit with a set amount of hitpoints, damage, range, and speed.
     * However, the unit sprite must be set separately with the setSprite() method.
     * @param healthLevel
     * @param damageLevel
     * @param range
     * @param speed
     */
    public Friendly(int healthLevel, int damageLevel, int range, int speed)
    {
        super(healthLevel, damageLevel, range, (speed * -1),null ,null);
        isEnemy = false;
    }

    /**
     * Moves Friendly Unit from the left side of the screen to the
     * right side of the screen. The unit's overall speed is set
     * relative to its movement speed, and the set scaling period
     * which maintains a constant time for the unit to cross the
     * board depending on the size of the screen used.
     */
    public void move()
    {
        pos.setLocation(pos.getX() - moveSpeed ,  pos.getY());
    }


    /**
     * Spawns a Friendly Unit onto the board in a given row
     * @param row Value Ranging from 1-3 to represent row unit is spawned on.
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
            y = CastleDefense.ROW1Y;
        else if (row == 2)
            y = CastleDefense.ROW2Y;
        else
            y = CastleDefense.ROW3Y;
        //bounds.add(x, y);
        pos.setLocation(x, y);
    }

    /**
     * Kills a Friendly Unit and removes it
     * from all ArrayLists and containers.
     */
    public void kill()
    {
        if(Main.VERBOSE) System.out.println(this + " DYING");
        //Main.b.removeUnit(this); //CAUSES CRASH WHEN ELEMENTS ARE BEING MODIFIED
        isDead = true;
        pos.setLocation(5,5);
        Main.menu.repaint();
    }

    /**
     * Returns The Cost Of a Given Type Of Unit.
     * @param f Name Of Friendly Unit, As a String
     * @return Price To Spawn Unit In
     */
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
