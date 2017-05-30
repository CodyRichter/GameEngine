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
            super(1, 1, 1, 1,0, null);
            this.setSprite("peasant");
        }

        @Override
        public String toString() {
            return "Enemy";
        }
    }

    public static void main(){
        Main.b.setMessage("Hello! Welcome to Castle Defense!");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Enemy e = new TutorialEnemy();
                e.spawn(1);
                Main.b.setMessage("This is an Enemy Soldier");
            }
        },5000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {Main.b.setMessage("You Must Defend Our Land!");
            }
        },10000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {Main.b.setMessage("Press [1] to Spawn Militia!");
            }
        },15000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setMessage("Press Arrow Keys to change the Row.");
            }
        },20000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setMessage("Good Luck Commander!");
            }
        },25000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setMessage("");
            }
        },30000);
    }
}
