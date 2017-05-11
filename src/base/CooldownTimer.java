package base;

/**
 * Created by Cody on 5/10/2017.
 */
public class CooldownTimer {

    private int originalTime;
    private int timeRemaining;


    public CooldownTimer(int time)
    {
        originalTime = time;
        timeRemaining = time;
    }

    /*
    Returns Time Remaining On Timer
     */
    public int getTimeRemaining()
    {
        return timeRemaining;
    }

    /*
    Starts The Timer From The Current Position.
     */
    public void startTimer(int timeToRunFor) throws InterruptedException {

    }

    /*
    Stops Timer From Counting Down.
     */
    public void stopTimer()
    {
        Thread.currentThread().interrupt();
    }

    /*
    Resets Timer To Run From Original Time
     */
    public void reset()
    {
        stopTimer();
        timeRemaining = originalTime;
    }

}