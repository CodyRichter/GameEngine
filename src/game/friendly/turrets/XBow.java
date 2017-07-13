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
public class XBow extends Turret {

    private static Timer timer = new Timer();
    private static boolean isComplete = true;
    public static final int COST = 750;
    protected static int cooldown = 60;
    protected static int damageAmount = 1;

    public XBow() {
        super(15, damageAmount, 50, 10);
        this.setSprite("xbow");
    }

    @Override
    public void attack(Unit u) {
        Timer t = new Timer();
        Turret turret = this;

        Projectile p = new Projectile();
        p.spawn(turret);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Projectile p = new Projectile();
                p.spawn(turret);
            }
        }, 300);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Projectile p = new Projectile();
                p.spawn(turret);
            }
        }, 600);

        doingAction = true;

        //Sets Timer For Cooldown
        Timer t2 = new Timer();
        t2.schedule(new TimerTask() {
            @Override
            public void run() {
                doingAction = false;
                currentlyAttacking = false;
            }
        }, (int)(delayBetweenAttacks*1000));
    }

    public String toString(){return "X-Bow";}

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
