package game;

import base.Board;
import base.Unit;
import game.friendly.Catapult;
import game.friendly.Infantry;
import game.friendly.Knight;
import game.friendly.Militia;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Frank Williams on 5/5/2017.
 */
public class CastleDefenseBoard extends Board implements KeyListener {

    public static int selectedRow = 1;
    private static int rowThing = 0;


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
        //Closes Game When [Esc] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CastleDefense.endGame();
            System.exit(0);
        }


        //Stops Input If Game Is Over
        if (CastleDefense.gameOver) return;

        /*
        Unit Spawning Row Selected
         */

        //Selects Row 1 With [1] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_1)
        {
            if(Main.VERBOSE) System.out.println("SELECTING ROW 1");
            selectedRow = 1;
            Main.menu.repaint();
        }

        //Selects Row 2 With [2] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_2)
        {
            if(Main.VERBOSE) System.out.println("SELECTING ROW 2");
            selectedRow = 2;
            Main.menu.repaint();
        }

        //Selects Row 3 With [3] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_3)
        {
            if(Main.VERBOSE) System.out.println("SELECTING ROW 3");
            selectedRow = 3;
            Main.menu.repaint();
        }

        /*
        Unit Spawning Cooldown
         */

        //Spawns In Friendly Unit "Militia" When [M] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_M)
        {
            if (Militia.isReadyToSpawn() && CastleDefense.getBalance() >= Militia.COST) {
                Militia.startCooldown();
                CastleDefense.subtractMoney(Militia.COST);
                Militia m = new Militia();
                m.spawn(selectedRow);
            }
        }

        //Spawns In Friendly Unit "Infantry" When [I] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_I)
        {
            if (Infantry.isReadyToSpawn() && CastleDefense.getBalance() >= Infantry.COST) {
                Infantry.startCooldown();
                CastleDefense.subtractMoney(Infantry.COST);
                Infantry i = new Infantry();
                i.spawn(selectedRow);
            }
        }

        //Spawns In Friendly Unit "Knight" When [K] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_K)
        {
            if (Knight.isReadyToSpawn() && CastleDefense.getBalance() >= Knight.COST) {
                Knight.startCooldown();
                CastleDefense.subtractMoney(Knight.COST);
                Knight k = new Knight();
                k.spawn(selectedRow);
            }
        }

        //Spawns In Catapult When [C] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_C){

            if (Catapult.isReadyToSpawn() && CastleDefense.getBalance() >= Catapult.COST) {
                Catapult.startCooldown();
                CastleDefense.subtractMoney(Catapult.COST);
                Catapult c = new Catapult();
                c.spawn(selectedRow);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            rowThing++;
            selectedRow = (rowThing % 3) + 1;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            if (rowThing < 0)
                rowThing = 3;
            rowThing--;
            selectedRow = (rowThing % 3) + 1;
        }


        //Moves On To Next Wave

        if(e.getKeyCode() == KeyEvent.VK_N){
            //CastleDefense.nextWave();
            CastleDefense.addMoney(1000);
        }

    }


    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

}
