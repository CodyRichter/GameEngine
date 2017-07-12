package game.friendly;

import base.Unit;
import game.Main;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Friendly Unit: Catapult
 *
 * @author Cody Richter
 * @version 1.0
 */
public class Catapult extends Friendly {
    //Spawning Cooldown Variables
    protected static int cooldown = 30;
    private static Timer timer = new Timer();
    private static boolean isComplete = true;
    public static final int COST = 1000;

    public Catapult() {
        super(10,0,100,1);
        this.setSprite("catapult");
        isSiegeWeapon = true;
        delayBetweenAttacks = 20;
    }

    @Override
    public void attack(Unit u) {
        Projectile p = new Projectile();
        p.spawn(this);
        doingAction = true;

        //Sets Timer For Cooldown
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                doingAction = false;
                currentlyAttacking = false;
            }
        }, (int)(delayBetweenAttacks*1000));
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

    public String toString(){return "Catapult";}

}
