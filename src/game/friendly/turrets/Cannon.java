package game.friendly.turrets;

import base.Unit;
import game.Main;
import game.friendly.Projectile;

import java.util.Timer;
import java.util.TimerTask;

/**
 * GameEngine - game.friendly.turrets
 *
 * @author Cannon
 * @version 1.0
 */
public class Cannon extends Turret {

    private static Timer timer = new Timer();
    private static boolean isComplete = true;
    public static final int COST = 350;
    protected static int cooldown = 45;
    protected static int damageAmount = 1;

    public Cannon() {
        super(10, damageAmount, 30, 2);
        this.setSprite("cannon");
    }

    @Override
    public void attack(Unit u) {
        Projectile p = new Projectile(damageAmount, false);
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

    public String toString(){return "Cannon";}

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

    public static int getUnitCost()
    {
        if (COST <= 0) return -1;
        else return COST;
    }
}
