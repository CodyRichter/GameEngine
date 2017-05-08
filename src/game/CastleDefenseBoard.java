package game;

import base.Board;

/**
 * Created by Frank Williams on 5/5/2017.
 */
public class CastleDefenseBoard extends Board {

    GameMenu menu;
    public CastleDefenseBoard (double inputWidth, double inputHeight){
        super(inputWidth, inputHeight);
        int arbitraryInt = 3; //should the menu size be a double?
        menu = new GameMenu(arbitraryInt); //should the menu size be a double?
    }
}
