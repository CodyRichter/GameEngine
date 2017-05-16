package game.friendly;

import game.Friendly;
import game.Main;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cody on 5/11/2017.
 */
public class Knight extends Friendly {

    //Spawning Cooldown Variables
    protected static int cooldown = 20;
    private static Timer timer = new Timer();
    private static boolean isComplete = true;
    protected static int cost = 350;

    public Knight()
    {
        super(12, 4, 1, 3, null);
        this.setSprite("knight");
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
