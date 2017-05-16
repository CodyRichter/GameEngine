package game;

import java.util.ArrayList;

/**
 * Created by Frank Williams on 5/8/2017.
 */
public class CastleDefense {
    private static int wave = 0;
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Friendly> friendlies = new ArrayList<Friendly>();

    public static void nextWave(){

    }

    public static void testEnemySpawn(){

    }
    public static void addEnemy(Enemy e){
        enemies.add(e);
    }


    public void doAction(Friendly f)
    {
        //Basic Conditions That Need To Be Met
        if (f.getCurrentHealth() > 0 && f.getX() < Main.b.getWidth())
        {

        }
        else if (f.getCurrentHealth() <= 0)
        {
            f.kill();
        }
    }

    public void doAction(Enemy e)
    {

    }

}
