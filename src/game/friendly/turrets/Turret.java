package game.friendly.turrets;

import game.CastleDefense;
import game.CastleDefenseBoard;
import game.Main;
import game.friendly.Friendly;

/**
 * GameEngine - game.friendly.turrets
 *
 * @author Turret
 * @version 1.0
 */
public abstract class Turret extends Friendly {

    public Turret(int healthLevel, int damageLevel, int range, int reload)
    {
        super(healthLevel, damageLevel, range, 0);
        delayBetweenAttacks = reload; // (In Seconds)
    }

    /*
    Spawns the turret based on the variable @selectedRow and @selectedCol in CastleDefenseBoard
     */
    public void spawn(){
        int row = CastleDefenseBoard.selectedRow;
        int col = CastleDefenseBoard.selectedColCoordinate;
        if (row > 3 || row < 1) return; //Will Ensure Unit Is Spawned In Correct Row

        //Adds Unit To List Of Units On Gameboard
        Main.b.addUnit(this);
        CastleDefense.addFriendly(this);

        //Sets X and Y Coordinates Of Spawned Unit
        int x = col;
        int y;
        if (row == 1)
            y = CastleDefense.ROW1Y;
        else if (row == 2)
            y = CastleDefense.ROW2Y;
        else
            y = CastleDefense.ROW3Y;
        pos.setLocation(x, y);
    }


}
