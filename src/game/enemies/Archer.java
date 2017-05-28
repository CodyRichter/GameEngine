package game.enemies;

import base.Unit;
import game.Enemy;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Enemy Unit: Archer
 * 
 * @author Cody Richter 
 * @version 1.0
 */
public class Archer extends Enemy
{
    public Archer()
    {
        super(5, 1, 20, 1,100, null);
        this.setSprite("archer");
        this.delayBetweenAttacks = 2;
    }
    @Override
    public void attack(Unit u) {
        ArcherProjectile p = new ArcherProjectile();
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



    public String toString(){return "Archer";}

}
