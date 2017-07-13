package game.friendly;

import base.Unit;
import game.CastleDefense;
import game.Main;
import game.friendly.turrets.Turret;

import javax.imageio.ImageIO;
import java.io.InputStream;

/**
 * Friendly Unit: Crossbowman Projectile (90kg Rock Lobbed Over a Distance Of 300m)
 *
 * @author Cody Richter
 * @version 1.0
 */
public class Projectile extends Friendly {
    public Projectile(){
        super(1,2,1,6);
        areaAttack = true;
        isProjectile = true;
        delayBetweenAttacks = 0;

        InputStream in = getClass().getResourceAsStream("/game/images/cProjectile.png");
        try {
            this.setSprite(ImageIO.read(in));
        } catch (Exception e){
            System.out.println("Exception when setting cProjectile " + e.getMessage());
        }

    }

    public Projectile(int damage, boolean isAreaAttack)
    {
        super(1, damage,1,4);
        areaAttack = isAreaAttack;
        isProjectile = true;
        delayBetweenAttacks = 0;

        InputStream in = getClass().getResourceAsStream("/game/images/turretProjectile.png");
        try {
            this.setSprite(ImageIO.read(in));
        } catch (Exception e){
            System.out.println("Exception when setting turretProjectile " + e.getMessage());
        }

    }

    public void spawn(Crossbowman c){

        //Adds Unit To List Of Units On Gameboard
        Main.b.addUnit(this);
        CastleDefense.addFriendly(this);

        //Sets X and Y Coordinates Of Spawned Unit
        int x = (int) c.getX();
        int y = (int) c.getY();

        pos.setLocation(x, y);
    }

    public void spawn(Turret t){

        //Adds Unit To List Of Units On Gameboard
        Main.b.addUnit(this);
        CastleDefense.addFriendly(this);

        //Sets X and Y Coordinates Of Spawned Unit
        int x = (int) t.getX();
        int y = (int) t.getY();

        pos.setLocation(x, y);
    }

    @Override
    public void attack(Unit u) {
        if ((!this.hasAreaAttack() && doingAction && currentHealth > 0)) return;
        this.damage(1);
        u.damage(damage);
    }

    public String toString(){return "";}

}
