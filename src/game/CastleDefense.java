package game;

import java.util.ArrayList;

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

    //Lists Holding All Units Spawned In On Board
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Friendly> friendlies = new ArrayList<Friendly>();

    //Money Player Has In Game - Set This Value To Be The Amount of Starting Money Player Has
    private static int balance = 400;

    public static void nextWave(){

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
        if (u.getCurrentHealth() > 0 && u.getX() < Main.b.getWidth()-50)
        {
            //Will Exit Method If Unit Is Currently Completing an Action
            if (u.isInAction()) return;

            for (int i = (int)u.getX()+50; i < (50+(int)u.getX())+(u.getAttackRange())*20; i++)
            {
                for(Enemy e : enemies) {
                    if (e.getX() == i) {
                        u.attack(e);
                    }
                }
            }

            u.move();

        }
        else
        {


        }
    }

    public static void doAction(Enemy u)
    {
        //Basic Conditions That Need To Be Met
        if (u.getCurrentHealth() > 0 && u.getX() > 0)
        {
                //Will Exit Method If Unit Is Currently Completing an Action
            if (u.isInAction()) return;

               for (int i = (int)u.getX(); i < (int)u.getX()+(u.getAttackRange())*20; i++)
               {
                  for(Friendly f : friendlies) {
                     if (f.getX() == i) {
                           u.attack(f);
                       }
                   }
               }

                u.move();
        }
        else
        {

        }
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

    }
