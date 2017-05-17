package game.friendly;

import game.Friendly;
import game.Main;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cody on 5/11/2017.
 */
public class Infantry extends Friendly {

    //Spawning Cooldown Variables
    protected static int cooldown = 10;
    private static Timer timer = new Timer();
    private static boolean isComplete = true;
    protected static int cost = 200;

    public Infantry()
    {
        super(10, 3, 1, 1, null);
        this.setSprite("infantry");
    }

    public void attack()
    {

    }

    public void kill()
    {

    }

    public static void startCooldown()
    {
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
        if (cost <= 0) return -1;
        else return cost;
    }

}