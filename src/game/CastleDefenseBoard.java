package game;

import base.Board;
import base.Unit;
import game.enemies.Assassin;
import game.friendly.Crossbowman;
import game.friendly.Infantry;
import game.friendly.Knight;
import game.friendly.Militia;
import game.friendly.turrets.Barricade;
import game.friendly.turrets.Cannon;
import game.friendly.turrets.XBow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Frank Williams on 5/5/2017.
 */
public class CastleDefenseBoard extends Board implements KeyListener {

    public static int selectedRow = 1;
    public static int selectedColCoordinate = 0;
    public static boolean showUnitMenu = false;
    public static boolean showInfoMenu = false;
    public static boolean defensePlacementMode = false; //Toggles Placement of Static Defenses

    public CastleDefenseBoard() {
        addKeyListener(this); //Registers New Key Listener To Board
    }

    @Override
    public void addUnit(Unit u) {
        super.addUnit(u);
    }

    public static int getCoordinateFromRow(int selectedRow) {
        int row;
        if (CastleDefenseBoard.selectedRow == 1) row = CastleDefense.ROW1Y;
        else if (CastleDefenseBoard.selectedRow == 2) row = CastleDefense.ROW2Y;
        else row = CastleDefense.ROW3Y;
        return row;
    }

    //
    //
    // Key Listeners Below Here
    //
    //

    public void keyPressed(KeyEvent e) {
        //Closes Game When [Esc] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CastleDefense.endGame();
            System.exit(0);
        }


        //Stops Input If Game Is Over
        if (CastleDefense.gameOver) return;


        //Spawns In Friendly Unit "Militia" When [1] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_1) {
            if (!defensePlacementMode && Militia.isReadyToSpawn() && CastleDefense.getBalance() >= Militia.COST) {
                Militia.startCooldown();
                CastleDefense.subtractMoney(Militia.COST);
                Militia m = new Militia();
                m.spawn(selectedRow);
            } else if (defensePlacementMode && Cannon.isReadyToSpawn() && CastleDefense.getBalance() >= Cannon.COST) {
                if (!CastleDefense.checkTurretSpawn(selectedColCoordinate, getCoordinateFromRow(selectedRow))) return;
                Cannon.startCooldown();
                CastleDefense.subtractMoney(Cannon.COST);
                Cannon c = new Cannon();
                c.spawn();
            }
        }

        //Spawns In Friendly Unit "Infantry" When [2] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_2) {
            if (!defensePlacementMode && Infantry.isReadyToSpawn() && CastleDefense.getBalance() >= Infantry.COST) {
                Infantry.startCooldown();
                CastleDefense.subtractMoney(Infantry.COST);
                Infantry i = new Infantry();
                i.spawn(selectedRow);
            } else if (defensePlacementMode && Barricade.isReadyToSpawn() && CastleDefense.getBalance() >= Barricade.COST) {
                if (!CastleDefense.checkTurretSpawn(selectedColCoordinate, selectedRow)) return;
                Barricade.startCooldown();
                CastleDefense.subtractMoney(Barricade.COST);
                Barricade b = new Barricade();
                b.spawn();
            }
        }

        //Spawns In Friendly Unit "Knight" When [3] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_3) {
            if (!defensePlacementMode && Knight.isReadyToSpawn() && CastleDefense.getBalance() >= Knight.COST) {
                Knight.startCooldown();
                CastleDefense.subtractMoney(Knight.COST);
                Knight k = new Knight();
                k.spawn(selectedRow);
            } else if (defensePlacementMode && XBow.isReadyToSpawn() && CastleDefense.getBalance() >= XBow.COST) {
                if (!CastleDefense.checkTurretSpawn(selectedColCoordinate, selectedRow)) return;
                XBow.startCooldown();
                CastleDefense.subtractMoney(XBow.COST);
                XBow x = new XBow();
                x.spawn();
            }

        }

        //Spawns In Crossbowman When [4] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_4) {

            if (!defensePlacementMode && Crossbowman.isReadyToSpawn() && CastleDefense.getBalance() >= Crossbowman.COST) {
                Crossbowman.startCooldown();
                CastleDefense.subtractMoney(Crossbowman.COST);
                Crossbowman c = new Crossbowman();
                c.spawn(selectedRow);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_5 && Main.ADMINMODE) {
            Assassin a = new Assassin();
            a.spawn(2);
        }

        //Will Move Selected Row Down 1 When [Down Arrow] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (selectedRow == 3)
                selectedRow = 1;
            else
                selectedRow++;
            Main.menu.repaint();
        }

        //Will Move Selected Row Up 1 When [Up Arrow] Is Pressed
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (selectedRow == 1)
                selectedRow = 3;
            else
                selectedRow--;
            Main.menu.repaint();
        }
        //Will Move Turret Column Right
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && defensePlacementMode) {
            if (selectedColCoordinate >= CastleDefense.TURRET_PLACEMENT_LIMIT - (int) (Main.heightFactor * 100))
                return;
            selectedColCoordinate += 10;
        }

        //Will Move Turret Column Left
        if (e.getKeyCode() == KeyEvent.VK_LEFT && defensePlacementMode) {
            if (selectedColCoordinate <= 0)
                return;
            selectedColCoordinate -= 10;
        }


        //Will Toggle Visibility Of Unit Info Menu
        if (e.getKeyCode() == KeyEvent.VK_U) {
            showInfoMenu = false;
            showUnitMenu = !showUnitMenu;
            Main.menu.repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_I) {
            showUnitMenu = false;
            showInfoMenu = !showInfoMenu;
            Main.menu.repaint();
        }

        //Toggles Static Defense Placement Mode
        if (e.getKeyCode() == KeyEvent.VK_D) {
            defensePlacementMode = !defensePlacementMode;
            Main.menu.repaint();
        }

        //Change scale
        if (e.getKeyCode() == KeyEvent.VK_EQUALS) {
            if (Main.factor == 0) return;
            else
                Main.factor--;

            Main.widthFactor = (Main.screenWidth / Main.FACTORS[Main.factor][0]);
            Main.heightFactor = (Main.screenHeight / Main.FACTORS[Main.factor][1]);
            Main.menu.repaint();
            Main.b.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_MINUS) {
            if (Main.factor == Main.FACTORS.length - 1) return;
            else
                Main.factor++;

            Main.widthFactor = (Main.screenWidth / Main.FACTORS[Main.factor][0]);
            Main.heightFactor = (Main.screenHeight / Main.FACTORS[Main.factor][1]);
            Main.menu.repaint();
            Main.b.repaint();
        }

        //ADMIN FEATURE - Adds Money To Player Account
        if (e.getKeyCode() == KeyEvent.VK_N && Main.ADMINMODE) {
            CastleDefense.addMoney(1000);
        }

        if (e.getKeyCode() == KeyEvent.VK_V && Main.ADMINMODE) {
            Main.VERBOSE = !Main.VERBOSE;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && CastleDefense.getWave() >= 1) {
            if (!CastleDefense.paused) {
                titleMessage = (!CastleDefense.paused ? "[Game Paused]" : "");
                CastleDefense.pause();
            } else {
                titleMessage = (!CastleDefense.paused ? "[Game Paused]" : "");
                CastleDefense.resume();
            }


            Main.b.repaint();

        }

    }


    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}
