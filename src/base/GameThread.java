package base;

import javax.swing.*;

/**
 * Threaded Support For Game
 * 
 * @author Cody Richter
 * @version 1.0
 */
public class GameThread implements Runnable
{
    private JPanel gameBoard;
    private JPanel gameMenu;

    public GameThread(JPanel board, JPanel menu)
    {
        gameBoard = board;
        gameMenu = menu;
        //ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        //executor.scheduleAtFixedRate(this, 0, 20 , TimeUnit.MILLISECONDS);
    }

    public void run()
    {
        //gameMenu.repaint(); //Updates Graphics On Menu
        gameBoard.repaint(); //Updates Graphics On Main Game Board

    }
}
