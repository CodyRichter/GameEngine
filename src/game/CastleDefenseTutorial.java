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

    public static void main(){
        Main.b.setMessage("Hello! Welcome to castle defense!");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Enemy e = new Peasant();
                e.spawn(1);
                Main.b.setMessage("This is an enemy fighter from the Kingdom of Addison Wesley.");
            }
        },5000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setMessage("You're trying to protect your castle from the enemy horde.");
            }
        },6000);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.b.setMessage("Press [1] to spawn a militia to defeat the enemy.");
                try {
                    Main.update.sleep(5000);
                } catch(Exception e){
                    System.out.println("Exception in tutorial " + e.getMessage());
                }

            }
        },10000);
    }
}
