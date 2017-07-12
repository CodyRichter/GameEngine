package game.enemies;

import base.Unit;
import game.CastleDefense;
import game.Main;

import javax.imageio.ImageIO;
import java.io.InputStream;

/**
 * Enemy Projectile: Archer Projectile (Arrow)
 *
 * @author Cody Richter
 * @version 1.0
 */
public class ArcherProjectile extends Enemy {
    public ArcherProjectile() {
        super(1,1,1,5,0,null);
        areaAttack = false;
        isProjectile = true;
        delayBetweenAttacks = 0;

        InputStream in = getClass().getResourceAsStream("/game/images/aProjectile.png");
        try {
            this.setSprite(ImageIO.read(in));
        } catch (Exception e){
            System.out.println("Exception when setting aProjectile " + e.getMessage());
        }

    }
    public void spawn(Archer a){

        //Adds Unit To List Of Units On Gameboard
        Main.b.addUnit(this);
        CastleDefense.addEnemy(this);

        //Sets X and Y Coordinates Of Spawned Unit
        int x = (int) a.getX();
        int y = (int) a.getY();

        pos.setLocation(x, y);
    }

    @Override
    public void attack(Unit u) {
        if ((!this.hasAreaAttack() && doingAction)) return;
        u.damage(damage);
        this.damage(1);
    }


    @Override
    public String toString() {
        return "";
    }
}
