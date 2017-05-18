package base;

import game.CastleDefense;
import game.Enemy;
import game.Friendly;
import game.Main;

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

    //Economy Support
    private int fraction = 1;
    //Set This Amount To Control Speed That Player Earns Money
    private int fractionIncrement = 1;
    //Set This Amount To Control How Much Money Is Given Each Cycle
    private int amountPerCycle = 5;

    public GameOperationThread(JPanel board, JPanel menu)
    {
        gameBoard = board;
        gameMenu = menu;
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this, 0, 20 , TimeUnit.MILLISECONDS);
    }

    public void run()
    {
        if (CastleDefense.gameOver) return;

        //Updates Unit's Positions On Board And Checks Their Actions
        for(Enemy e : CastleDefense.enemies){
            CastleDefense.doAction(e);
        }
        for(Friendly f : CastleDefense.friendlies){
            CastleDefense.doAction(f);
        }

        //Adds Money To Player Over Time
        fraction += fractionIncrement;
        if (fraction >= 100) {
            CastleDefense.addMoney(amountPerCycle);
            fraction = 0;
            Main.menu.repaint();
        }
    }
}
