package game.friendly.turrets;

import base.Unit;
import game.Startup;

import java.util.Timer;
import java.util.TimerTask;

/**
 * GameEngine - game.friendly.turrets
 *
 * @author Cannon
 * @version 1.0
 */
public class Barricade extends Turret {

    private static Timer timer = new Timer();
    private static boolean isComplete = true;
    public static final int COST = 500;
    protected static int cooldown = 60;
    protected static int damageAmount = 0;

    public Barricade() {
        super(30, damageAmount, 0, 0);
        this.setSprite("barricade");
    }

    @Override
    public void attack(Unit u) {}

    public String toString(){return "Barricade";}

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

    public static int getUnitCost()
    {
        if (COST <= 0) return -1;
        else return COST;
    }
}
