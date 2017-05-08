package game;

import game.enemies.BasicEnemy;

import java.util.ArrayList;

/**
 * Created by Frank Williams on 5/8/2017.
 */
public class CastleDefense {
    private static int wave;
    private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public static void nextWave(){
        wave++;
    }
    public static void addEnemy(String type){
        if (type == "basic"){
            enemies.add(new BasicEnemy());
        }
    }
}
