package game.friendly;

import game.Friendly;
import game.Main;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Friendly Unit: Infantry
 *
 * @author Cody Richter
 * @version 1.0
 */
public class Infantry extends Friendly {

    //Spawning Cooldown Variables
    protected static int cooldown = 10;
    private static Timer timer = new Timer();
    private static boolean isComplete = true;
    public static final int COST = 200;

    public Infantry()
    {
        super(10, 3, 1, 1);
        delayBetweenAttacks = 2;
        this.setSprite("infantry");
    }

    public void attack()
    {

    }


    public static void startCooldown()
    {
        if (Main.ADMINMODE && Main.NOCOOLDOWN) return;
        isComplete = false;
        Main.menu.repaint();

        //Sets Timer For Cooldown
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                isComplete = true;
                Main.menu.repaint();
            }
        }, cooldown*1000);
    }

    public static boolean isReadyToSpawn()
    {
        return isComplete;
    }

    //
    // Economy
    //

    public static int getUnitCost()
    {
        if (COST <= 0) return -1;
        else return COST;
    }

    public String toString(){return "Infantry";}

}
