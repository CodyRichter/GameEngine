package game.friendly;

import game.Friendly;
import game.Main;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cody on 5/11/2017.
 */
public class Militia extends Friendly {

    //Spawning Cooldown Variables
    protected static int cooldown = 1;
    private static Timer timer = new Timer();
    private static boolean isComplete = true;

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
        isComplete = false;
        Main.menu.repaint();

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
