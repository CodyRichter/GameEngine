package game.enemies;

import game.Enemy;

/**
 * game.Enemy base.Unit: game.enemies.Peasant
 * 
 * @author Cody Richter 
 * @version 1.0
 */
public class Peasant extends Enemy
{
    /**
     * [game.enemies.Peasant]
     * Health: 3
     * Damage: 1
     * Range: 1
     * Speed: 1
     *
     */
    public Peasant()
    {
        super(3, 1, 1, 1, null);
        this.setSprite("peasant");
    }

    public void onDeath()
    {

    }

    public void attack()
    {

    }
}
