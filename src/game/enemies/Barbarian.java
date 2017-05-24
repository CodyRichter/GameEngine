package game.enemies;

import game.Enemy;

/**
 * game.Enemy base.Unit: game.enemies.Peasant
 * 
 * @author Cody Richter 
 * @version 1.0
 */
public class Barbarian extends Enemy
{
    public Barbarian()
    {
        super(14, 2, 1, 1,70, null);
        delayBetweenAttacks = 2;
        this.setSprite("barbarian");
    }

    public String toString(){return "Barbarian";}

}
