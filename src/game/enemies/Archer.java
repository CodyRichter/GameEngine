package game.enemies;

import game.Enemy;

/**
 * game.Enemy base.Unit: game.enemies.Peasant
 * 
 * @author Cody Richter 
 * @version 1.0
 */
public class Archer extends Enemy
{
    public Archer()
    {
        super(5, 1, 10, 1,30, null);
        this.setSprite("archer");
        this.delayBetweenAttacks = 3;
    }

    public String toString(){return "Archer";}

}
