package game;

import base.Board;
import base.Unit;
import game.enemies.Peasant;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Frank Williams on 5/5/2017.
 */
public class CastleDefenseBoard extends Board implements KeyListener {

    public CastleDefenseBoard (){
        addKeyListener(this); //Registers New Key Listener To Board
    }

    @Override
    public void addUnit(Unit u) {
        super.addUnit(u);
        if(u instanceof Enemy){
            // check if it is an enemy and then add it to a random place off the board

        } else if (u instanceof Friendly){
            //check if it isnt an enemy and add it to the user's side of the board
        }
    }


    //
    //
    // Key Listeners Below Here
    //
    //

    public void keyPressed(KeyEvent e)
    {
        super.setBackgroundColor("ffffff");
    }


    public void keyReleased(KeyEvent e)
    {

    }

    public void keyTyped(KeyEvent e)
    {

    }

}
