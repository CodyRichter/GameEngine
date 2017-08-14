package game.enemies;

import game.CastleDefense;
import game.Startup;

import java.util.Timer;
import java.util.TimerTask;

/**
 * GameEngine - game.enemies
 *
 * @author EnragedPeasant
 * @version 1.0
 */
public class EnragedPeasant extends Peasant {
    private boolean hasSpawned = false;

    public EnragedPeasant()
    {
     super(true);
    }

    public void spawn(int row){
            if (!hasSpawned) {
                spawnAfterDelay(800, row);
                spawnAfterDelay(1600, row);
                hasSpawned = true;
            }

            if (row > 3 || row < 1) return;
            EnragedPeasant e = new EnragedPeasant();
            Startup.b.addUnit(e);
            CastleDefense.enemies.add(e);
            int x = Startup.b.getWidth();
            int y;
            if (row == 1) e.setRow(1);
            else if (row == 2) e.setRow(2);
            else e.setRow(3);

            e.pos.setLocation(x,e.getY());
    }

    public void spawnAfterDelay(int delay, int row)
    {
        Timer spawnTimer = new Timer();
        spawnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            spawn(row);
            }
        }, delay);
    }

    public String toString(){return "Enraged Peasant";}
}

