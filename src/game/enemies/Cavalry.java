package game.enemies;

import game.Enemy;

/**
 * Created by frank2williams on 5/22/17.
 */
public class Cavalry extends Enemy {
    public Cavalry() {
        super(7,2,2,3,120,null);
        setSprite("cavalry");
    }

    public String toString(){return "Cavalry";}

}
