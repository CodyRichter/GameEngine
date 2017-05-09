package game;

import base.Unit;

/**
 * Methods For game.Enemy Units
 * 
 * @author Cody Richter
 * @version 1.0
 */
public abstract class Enemy extends Unit
{
    /**
     * Makes new enemy unit
     */
    public Enemy()
    {
        super(1,1,1,-1,null,null);
    }

    /**
     * Makes new enemy unit with given attributes
     */
    public Enemy(int healthLevel, int damageLevel, int range, int speed)
    {
        super(healthLevel, damageLevel, range, (speed * -1),null,null);
    }


}
