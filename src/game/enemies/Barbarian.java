package game.enemies;

import game.Enemy;

/**
 * Enemy Unit: Barbarian
 * 
 * @author Cody Richter 
 * @version 1.0
 */
public class Barbarian extends Enemy
{
    public Barbarian()
    {
        super(14, 2, 1, 1,150, null);
        delayBetweenAttacks = 2;
        this.setSprite("barbarian");
    }

    public String toString(){return "Barbarian";}

}
