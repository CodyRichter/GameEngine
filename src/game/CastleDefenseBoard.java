package game;

import base.Board;
import base.Unit;

/**
 * Created by Frank Williams on 5/5/2017.
 */
public class CastleDefenseBoard extends Board {

    GameMenu menu;
    public CastleDefenseBoard (){
        int arbitraryInt = 3; //should the menu size be a double?
        menu = new GameMenu(arbitraryInt); //should the menu size be a double?
    }

    @Override
    public void addUnit(Unit u) {
        super.addUnit(u);
        if(u instanceof Enemy){
            // check if it is an enemy and then add it to a random place off the board
        } else if (u instanceof Friendly || u instanceof Peasant){
            //check if it isnt an enemy and add it to the user's side of the board
        }
    }
}
