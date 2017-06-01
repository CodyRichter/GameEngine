package game;

import base.Board;
import base.GameOperationThread;
import game.enemies.Peasant;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Frank Williams on 5/30/2017.
 */
public class CastleDefenseTutorial {
    private static Timer t = new Timer();

    private static class TutorialEnemy extends Enemy {
        public TutorialEnemy(){
            super(3, 0, 1, 0.5,40, null);
            this.setSprite("peasant");
        }

        @Override
        public String toString() {
            return "Enemy";
        }
    }

    public static void main(){
        Main.b.setMessage("Hello! Welcome to castle defense!");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Enemy e = new TutorialEnemy();
                e.spawn(1);
                Main.b.setMessage("This is an enemy fighter from the Kingdom of Addison Wesley.");
            }
        },5000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {Main.b.setMessage("You're trying to protect your castle from the enemy horde.");
            }
        },10000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {Main.b.setMessage("Press [1] to spawn a militia to defeat the enemy.");
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
                Main.b.setMessage("Good luck");
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
