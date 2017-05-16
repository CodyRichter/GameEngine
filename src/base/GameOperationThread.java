package base;

import game.CastleDefense;
import game.Enemy;
import game.Friendly;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Threaded Support For Game
 * 
 * @author Cody Richter
 * @version 1.0
 */
public class GameOperationThread implements Runnable
{
    private JPanel gameBoard;
    private JPanel gameMenu;

    public GameOperationThread(JPanel board, JPanel menu)
    {
        gameBoard = board;
        gameMenu = menu;
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this, 0, 20 , TimeUnit.MILLISECONDS);
    }

    public void run()
    {
        for(Enemy e : CastleDefense.enemies){
            CastleDefense.doAction(e);
        }
        for(Friendly f : CastleDefense.friendlies){
            CastleDefense.doAction(f);
        }
    }
}
