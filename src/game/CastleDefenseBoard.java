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
    public static boolean showUnitMenu = false;
    public static boolean showInfoMenu = false;


    public CastleDefenseBoard (){
        addKeyListener(this); //Registers New Key Listener To Board
    }

    @Override
    public void addUnit(Unit u) {
        super.addUnit(u);
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


        //Spawns In Friendly Unit "Militia" When [1] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_1)
        {
            if (Militia.isReadyToSpawn() && CastleDefense.getBalance() >= Militia.COST) {
                Militia.startCooldown();
                CastleDefense.subtractMoney(Militia.COST);
                Militia m = new Militia();
                m.spawn(selectedRow);
            }
        }

        //Spawns In Friendly Unit "Infantry" When [2] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_2)
        {
            if (Infantry.isReadyToSpawn() && CastleDefense.getBalance() >= Infantry.COST) {
                Infantry.startCooldown();
                CastleDefense.subtractMoney(Infantry.COST);
                Infantry i = new Infantry();
                i.spawn(selectedRow);
            }
        }

        //Spawns In Friendly Unit "Knight" When [3] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_3)
        {
            if (Knight.isReadyToSpawn() && CastleDefense.getBalance() >= Knight.COST) {
                Knight.startCooldown();
                CastleDefense.subtractMoney(Knight.COST);
                Knight k = new Knight();
                k.spawn(selectedRow);
            }
        }

        //Spawns In Catapult When [4] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_4){

            if (Catapult.isReadyToSpawn() && CastleDefense.getBalance() >= Catapult.COST) {
                Catapult.startCooldown();
                CastleDefense.subtractMoney(Catapult.COST);
                Catapult c = new Catapult();
                c.spawn(selectedRow);
            }
        }

        //Will Move Selected Row Down 1 When [Down Arrow] Is Pressed
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (selectedRow == 3)
                selectedRow = 1;
            else
                selectedRow++;
            Main.menu.repaint();
        }

        //Will Move Selected Row Up 1 When [Up Arrow] Is Pressed
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            if (selectedRow == 1)
                selectedRow = 3;
            else
                selectedRow--;
            Main.menu.repaint();
        }

        //Will Toggle Visibility Of Unit Info Menu
        if(e.getKeyCode() == KeyEvent.VK_U) {
            showInfoMenu = false;
            if (showUnitMenu)
                showUnitMenu = false;
            else
                showUnitMenu = true;
            Main.menu.repaint();
        }

        if(e.getKeyCode() == KeyEvent.VK_I) {
            showUnitMenu = false;
            if (showInfoMenu)
                showInfoMenu = false;
            else
                showInfoMenu = true;
            Main.menu.repaint();
        }

        //Change scale
        if(e.getKeyCode() == KeyEvent.VK_EQUALS){
            if (Main.factor == 0) return;
            else
                Main.factor--;

            Main.widthFactor = (Main.screenWidth/Main.FACTORS[Main.factor][0]);
            Main.heightFactor = (Main.screenHeight/Main.FACTORS[Main.factor][1]);
            Main.menu.repaint();
            Main.b.repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_MINUS){
            if (Main.factor == Main.FACTORS.length - 1) return;
            else
                Main.factor++;

            Main.widthFactor = (Main.screenWidth/Main.FACTORS[Main.factor][0]);
            Main.heightFactor = (Main.screenHeight/Main.FACTORS[Main.factor][1]);
            Main.menu.repaint();
            Main.b.repaint();
        }

        //ADMIN FEATURE - Adds Money To Player Account
        if(e.getKeyCode() == KeyEvent.VK_N && Main.ADMINMODE){
            CastleDefense.addMoney(1000);
        }

        if(e.getKeyCode() == KeyEvent.VK_V && Main.ADMINMODE){
            Main.VERBOSE  = !Main.VERBOSE;
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE && CastleDefense.getWave() >= 1){
            if(!CastleDefense.paused) {
                titleMessage = (!CastleDefense.paused ? "[Game Paused]" : "");
                CastleDefense.pause();
            }else {
                titleMessage = (!CastleDefense.paused ? "[Game Paused]" : "");
                CastleDefense.resume();
            }


            Main.b.repaint();

        }

    }


    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

}
