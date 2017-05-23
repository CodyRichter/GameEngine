package game.friendly;

import base.Unit;
import game.Friendly;
import game.Main;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by frank2williams on 5/23/17.
 */
public class Catapult extends Friendly {
    //Spawning Cooldown Variables
    protected static int cooldown = 10;
    private static Timer timer = new Timer();
    private static boolean isComplete = true;
    public static final int COST = 1000;

    public Catapult() {
        super(10,0,100,1);
        this.setSprite("peasant");
    }

    @Override
    public void attack(Unit u) {
        Projectile p = new Projectile();
        p.spawn(this.currentRow);
        while(p.getX() != this.getX())
            p.move();
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

    public class Projectile extends Friendly {
        public Projectile(){
            super(1,10,1,1);
            this.setSprite("peasant");
        }
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
        if (COST <= 0) return -1;
        else return COST;
    }
}
