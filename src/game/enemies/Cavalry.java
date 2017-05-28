package game.enemies;

import game.Enemy;

/**
 * Enemy Unit: Cavalry
 *
 * @author Cody Richter
 * @version 1.0
 */
public class Cavalry extends Enemy {
    public Cavalry() {
        super(7,2,2,3,170,null);
        setSprite("cavalry");
    }

    public String toString(){return "Cavalry";}

}
