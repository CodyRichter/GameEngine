package game;

import game.enemies.Enemy;
import game.friendly.Friendly;
import game.friendly.turrets.Cannon;
import game.friendly.turrets.Turret;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Frank Williams on 5/30/2017.
 */
public class CastleDefenseTutorial {
    private static Timer t = new Timer();

    private static class TutorialEnemy extends Enemy {
        public TutorialEnemy() {
            super(3, 0, 1, 1, 40, null);
            this.setSprite("peasant");
        }

        @Override
        public String toString() {
            return "Enemy";
        }
    }

    public static void main() {
        Startup.b.setTitleMessage("Hello! Welcome to Castle Defense!");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Enemy e = new TutorialEnemy();
                e.spawn(1);
                Startup.b.setTitleMessage("This is an Enemy Soldier");
            }
        }, 5000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("You Must Defend Our Land!");
            }
        }, 10000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("Press [1] to Spawn Militia!");
            }
        }, 15000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("Press Arrow Keys to Change the Row.");
                Enemy e = new TutorialEnemy();
                e.spawn(3);
            }
        }, 20000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("Press [I] for Controls.");
            }
        }, 25000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("Press [U] to see Available Units.");
            }
        }, 30000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("Press [D] to Toggle Placing Defenses");
            }
        }, 35000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("Use All 4 Arrow Keys To Set Turret Location");
            }
        }, 40000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                CastleDefense.turretPlacementLimit = 1;
                Startup.b.setTitleMessage("Place a Cannon to Stop this Enemy!");
                Startup.b.sendNotification("[Hint]: Press [1] To Spawn The Cannon");
                CastleDefense.addMoney(Cannon.COST);
                Enemy e = new TutorialEnemy();
                e.spawn(2);
            }
        }, 45000);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("After all friendly units cross the screen");
            }
        }, 50000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("The next wave will begin.");
            }
        }, 52500);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("Good Luck Commander!");
            }
        }, 55000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Startup.b.setTitleMessage("");
                for (Friendly f:CastleDefense.friendlies)
                    f.kill();
                for (Turret t:CastleDefense.turrets)
                    t.kill();
                CastleDefense.setMoney(CastleDefense.STARTING_BALANCE);
                CastleDefense.tutorial = false;
            }
        }, 60000);
    }
}
