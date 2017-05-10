package game;

import game.enemies.BasicEnemy;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Frank Williams on 5/8/2017.
 */
public class CastleDefense {
    private static int wave = 0;
    private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public static void nextWave(){
        wave++;
        if(wave <= 5) {
            for (int i = 0; i < 10; i++) {
                try{
                    Enemy enemyToAdd = new BasicEnemy();
                    Main.b.addUnit(enemyToAdd);
                    addEnemy(enemyToAdd);
                    enemyToAdd.spawn(Main.testSpawn);
                    System.out.println("Enemies spawned!");
                }catch (IOException ioe) {
                    System.out.println("IOException when spawning enemies");
                }
            }
        }
    }
    public static void addEnemy(Enemy e){
        enemies.add(e);
    }
}
