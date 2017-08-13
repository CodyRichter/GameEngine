package game.enemies;

/**
 * Enemy Unit: Peasant
 *
 * @author Cody Richter 
 * @version 1.0
 */
public class Peasant extends Enemy
{
    public Peasant()
    {
        super(3, 1, 1, 1,60, null);
        delayBetweenAttacks = 0.9;
        this.setSprite("peasant");
    }

    public Peasant(boolean enraged)
    {
        super(3, 1, 1, 1,60, null);
        if (enraged)
        {
            delayBetweenAttacks = 0.8;
            this.setSprite("peasant");
            rewardMoney = 70;
            attackRange = 1;
        }
    }

    public String toString(){return "Peasant";}

}
