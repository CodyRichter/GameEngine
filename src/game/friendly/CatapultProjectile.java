package game.friendly;

import game.Friendly;

/**
 * Created by Cody on 5/23/2017.
 */
public class CatapultProjectile extends Friendly {
    public CatapultProjectile(){
        super(3,10,1,5);
        areaAttack = true;
        this.setSprite("catapultProjectile");
    }
}
