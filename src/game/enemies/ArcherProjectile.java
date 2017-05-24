package game.enemies;

import game.CastleDefense;
import game.Enemy;
import game.Main;

import javax.imageio.ImageIO;
import java.io.InputStream;

/**
 * Created by frank2williams on 5/24/17.
 */
public class ArcherProjectile extends Enemy {
    public ArcherProjectile() {
        super(3,10,1,5,0,null);
        areaAttack = false;
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
    public String toString() {
        return "";
    }
}
