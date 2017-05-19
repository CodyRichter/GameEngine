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
    private static final int COST = 200;

    public Infantry()
    {
        super(10, 3, 1, 1, COST);
        this.setSprite("infantry");
    }

    public void attack()
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

}
