package base;

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
public class GameThread implements Runnable
{
    private JPanel panel;

    public GameThread(JPanel panel)
    {
        this.panel = panel;
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this, 0, 20 , TimeUnit.MILLISECONDS);
    }

    public void run()
    {
        panel.repaint();
    }
}
