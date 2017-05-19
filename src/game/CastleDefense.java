package game;

import game.enemies.Archer;
import game.enemies.Barbarian;
import game.enemies.Peasant;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Frank Williams on 5/8/2017.
 */
public class CastleDefense {

    //X-Values Of Board JPanel That Rows Are At And That Units Are Spawned At
    public static final int ROW1X = 100;
    public static final int ROW2X = 300;
    public static final int ROW3X = 500;

    //Current Wave Player Is On
    private static int wave = 0;
    private static int waveControlVariable = 0;
    public static boolean gameOver = false;
    public static boolean nextWave = false;

    //Lists Holding All Units Spawned In On Board
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Friendly> friendlies = new ArrayList<Friendly>();

    //Money Player Has In Game - Set This Value To Be The Amount of Starting Money Player Has
    private static int balance = 100;

    private static Timer t = new Timer();

    public static void main(int control){

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                int icontrol = control;
                int row = (int) (Math.random() * 3) + 1;
                int bossUnit = (int)((Math.random()*100)+1);
                if (bossUnit <= 10)
                {
                    Enemy e = new Archer();
                    e.spawn(row);
                }
                else if (bossUnit < 20)
                {
                    Enemy e = new Barbarian();
                    e.spawn(row);
                }
                else {
                    Enemy e = new Peasant();
                    e.spawn(row);
                }

                icontrol--;
                main(icontrol);
            }
        },5000);


    }

    public static void nextWave(){
        wave++;
        waveControlVariable = 3 * wave;
        for(Friendly f : friendlies){
            f.kill();
            addMoney(500);
        }
        main(waveControlVariable);
        Main.menu.repaint();
    }

    public static void testEnemySpawn(){

    }

    public static void addEnemy(Enemy e){
        enemies.add(e);
    }

    public static void addFriendly(Friendly f){friendlies.add(f);}

    public static void doAction(Friendly u)
    {
        //Basic Conditions That Need To Be Met: Unit Has Health Remaining And Isn't At Edge Of Board
        if (u.getX() < Main.b.getWidth()-50)
        {
            nextWave = false;
            //Will Exit Method If Unit Is Currently Completing an Action
            if (u.isInAction()) return;

            u.currentlyAttacking = false;
            for (int i = (int)u.getX()+50; i < (50+(int)u.getX())+(u.getAttackRange())*20; i++)
            {

                for(Enemy e : enemies) {
                    if (!e.isDead() && e.getX() == i && e.getY() == u.getY()) {
                        u.attack(e);
                        u.currentlyAttacking = true;
                    } else {

                    }


                }
            }

            if (u.getX() >= Main.b.getWidth()-51)
            {
                double multiplier = u.getCurrentHealth()/u.getMaxHealth();
                addMoney((int)(u.getUnitCost(u)*multiplier));
                u.kill();
            }

            if (!u.currentlyAttacking) {
                u.move();
            }



        }
        else
        {
            nextWave = true;
        }
    }

    public static void doAction(Enemy u)
    {
        //Basic Conditions That Need To Be Met
        if (u.getX() > 0)
        {
            nextWave = false;
            //Will Exit Method If Unit Is Currently Completing an Action
            if (u.isInAction()) return;

            u.currentlyAttacking = false;
            for (int i = (int)u.getX() - ((int)u.getAttackRange())*20; i < (int)u.getX(); i++)
            {

                for(Friendly f : friendlies) {
                    if (!f.isDead() && f.getX() + 50 == i && u.getY() == f.getY()) {
                        u.attack(f);
                        u.currentlyAttacking = true;
                    } else {

                    }
                }
            }

            if (!u.currentlyAttacking) {
                u.move();
            }
        }
        else if (u.getX() <= 0)
        {
            endGame();
        }
    }

    public static void endGame()
    {
        gameOver = true;
    }

    //
    //
    // Economy
    //
    //

    /**
     * Takes Money From Player's Balance
     */
    public static void subtractMoney(int amount)
    {
        if (amount < 0) {}
        if (amount > balance) balance = 0;
        else balance -= amount;
    }

    /**
     * Adds Money To Player's Balance
     */
    public static void addMoney(int amount)
    {
        if (amount < 0) {}
        else balance += amount;
    }

    public static int getBalance()
    {
        return balance;
    }

    public static int getWave() {return wave;}
}
