package game.friendly;

import game.Friendly;

/**
 * Created by Cody on 5/11/2017.
 */
public class Militia extends Friendly {

    protected static int cooldown = 1;
    protected static int remainingCooldown = 1;

    public Militia()
    {


    }

    public void attack()
    {

    }

    public void onDeath()
    {

    }

    public static void startCooldown()
    {
        remainingCooldown = cooldown;
    }

    public static int getCooldown()
    {
        return remainingCooldown;
    }

}
