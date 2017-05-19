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
        super(12, 2, 1, 1,120, null);
        this.setSprite("barbarian");
    }

}
