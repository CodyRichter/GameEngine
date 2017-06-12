package base;

import game.CastleDefense;
import game.friendly.Catapult;

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
public class GameGraphicsThread implements Runnable
{
    private JPanel gameBoard;
    private JPanel gameMenu;

    public GameGraphicsThread(JPanel board, JPanel menu)
    {
        gameBoard = board;
        gameMenu = menu;
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this, 0, 15 , TimeUnit.MILLISECONDS);
    }

    public void run()
    {
        if (CastleDefense.gameOver) return;
        if (CastleDefense.paused) return;
        gameBoard.repaint(); //Updates Graphics On Main Game Board
    }
}
