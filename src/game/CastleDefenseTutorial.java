package game;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Frank Williams on 5/30/2017.
 */
public class CastleDefenseTutorial {
    private static Timer t = new Timer();

    private static class TutorialEnemy extends Enemy {
        public TutorialEnemy(){
            super(3, 0, 1, 1,40, null);
            this.setSprite("peasant");
        }

        @Override
        public String toString() {
            return "Enemy";
        }
    }

    public static void main(){
        Main.b.setTitleMessage("Hello! Welcome to Castle Defense!");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Enemy e = new TutorialEnemy();
                e.spawn(1);
                Main.b.setTitleMessage("This is an Enemy Soldier");
            }
        },5000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {Main.b.setTitleMessage("You Must Defend Our Land!");
            }
        },10000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {Main.b.setTitleMessage("Press [1] to Spawn Militia!");
            }
        },15000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setTitleMessage("Press Arrow Keys to change the Row.");
                Enemy e = new TutorialEnemy();
                e.spawn(3);
            }
        },20000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setTitleMessage("Press [I] for controls.");
            }
        },25000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setTitleMessage("After all friendly units cross the screen");
            }
        },30000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setTitleMessage("The next wave will begin.");
            }
        },32500);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setTitleMessage("Good Luck Commander!");
            }
        },35000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setTitleMessage("");
            }
        },40000);
    }
}
