package game.friendly;

import game.CastleDefense;
import game.Friendly;
import game.Main;

/**
 * Created by Cody on 5/23/2017.
 */
public class CatapultProjectile extends Friendly {
    public CatapultProjectile(){
        super(3,10,1,5);
        areaAttack = true;
        delayBetweenAttacks = 0;
        this.setSprite("catapultProjectile");
    }

    public void spawn(Catapult c){

        //Adds Unit To List Of Units On Gameboard
        Main.b.addUnit(this);
        CastleDefense.addFriendly(this);

        //Sets X and Y Coordinates Of Spawned Unit
        int x = (int) c.getX();
        int y = (int) c.getY();

        pos.setLocation(x, y);
    }

    public String toString(){return "";}

}
