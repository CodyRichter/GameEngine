package game.friendly;

import base.Unit;
import game.Friendly;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by frank2williams on 5/23/17.
 */
public class Catapult extends Friendly {
    public Catapult() {
        super(10,0,100,1);
    }

    @Override
    public void attack(Unit u) {
        Projectile p = new Projectile();
        p.spawn(this.currentRow);
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
            super(1,10,1,10);
        }
    }
}
