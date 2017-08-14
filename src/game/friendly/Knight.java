package game.friendly;

import game.Startup;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Friendly Unit: Knight
 *
 * @author Cody Richter
 * @version 1.0
 */
public class Knight extends Friendly {

    //Spawning Cooldown Variables
    protected static int cooldown = 20;
    private static Timer timer = new Timer();
    private static boolean isComplete = true;
    public static final int COST = 350;

    public Knight()
    {
        super(12, 4, 1, 3);
        delayBetweenAttacks = 3;
        this.setSprite("knight");
    }

    public void attack()
    {

    }



    public static void startCooldown()
    {
        if (Startup.ADMINMODE && Startup.NOCOOLDOWN) return;
        isComplete = false;
        Startup.menu.repaint();

        //Sets Timer For Cooldown
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                isComplete = true;
                Startup.menu.repaint();
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

    public String toString(){return "Knight";}
}
