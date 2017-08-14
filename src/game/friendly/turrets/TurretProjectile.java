package game.friendly.turrets;

import base.Unit;
import game.CastleDefense;
import game.Startup;

import javax.imageio.ImageIO;
import java.io.InputStream;

/**
 * GameEngine - game.friendly.turrets
 *
 * @author TurretProjectile
 * @version 1.0
 */
public class TurretProjectile extends Turret {

    public TurretProjectile(int damageAmount, boolean hasAreaAttack)
    {
        super(1, damageAmount, 1, 0);
        areaAttack = hasAreaAttack;
        isProjectile = true;
        moveSpeed = 4;

        InputStream in = getClass().getResourceAsStream("/game/images/cProjectile.png");
        try {
            this.setSprite(ImageIO.read(in));
        } catch (Exception e){
            System.out.println("Exception when setting cProjectile " + e.getMessage());
        }
    }

    public void spawn(Turret t){

        //Adds Unit To List Of Units On Gameboard
        Startup.b.addUnit(this);
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
