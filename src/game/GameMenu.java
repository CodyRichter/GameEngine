package game;

import game.friendly.Catapult;
import game.friendly.Infantry;
import game.friendly.Knight;
import game.friendly.Militia;
import game.friendly.turrets.Barricade;
import game.friendly.turrets.Cannon;

import javax.swing.*;
import java.awt.*;

/**
 * Menu Displayed On Top Of Game Window. Dynamically Displays Information and Help to User.
 */
public class GameMenu extends JPanel {
    private Font normalFont;
    private Font titleFont;
    private JLabel rowSelectInfo;
    private int currentRow = 0;

    public GameMenu() {
        super();
        //this.setBackground(Color.decode("#42f448"));
        //Sets Font For Text In Menu


        normalFont = new Font("Verdana", Font.BOLD, (int)(24 * Main.heightFactor));
        titleFont = new Font("SansSerif", Font.ITALIC, (int)(50 * Main.heightFactor));

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayBoard(g);
    }

    private void displayBoard(Graphics g) {
        normalFont = new Font("Verdana", Font.BOLD, (int)(24 * Main.heightFactor));
        titleFont = new Font("SansSerif", Font.ITALIC, (int)(50 * Main.heightFactor));
        if (!CastleDefenseBoard.showUnitMenu && !CastleDefenseBoard.showInfoMenu) {

            //Shows Information On Each Unit Type
            g.setFont(normalFont);
            g.drawString("[U] Unit Information",0,((int)(Main.heightFactor *30)));
            g.drawString("[D] Toggle Defense Placement",0,((int)(Main.heightFactor *60)));
            g.drawString("[I] Game Information",0,((int)(Main.heightFactor *90)));

        } else if (CastleDefenseBoard.showUnitMenu) {
            g.setFont(normalFont);
            g.setColor(getCorrectColor(""));
            g.drawString("Avaliable", 0, ((int)(Main.heightFactor *50)));
            g.drawString("Units", 0, ((int)(Main.heightFactor *80)));
            g.fillRect((((int)(Main.widthFactor *130))),0,20, this.getHeight());

            //Avaliable Units
            if (!CastleDefenseBoard.defensePlacementMode) {
                //Information on Units
                g.setColor(getCorrectColor("militia"));
                g.drawString("[1] Militia - $" + Militia.getUnitCost(), ((((int) (Main.widthFactor * 150)))), ((int) (Main.heightFactor * 25)));

                g.setColor(getCorrectColor("infantry"));
                g.drawString("[2] Infantry - $" + Infantry.getUnitCost(), ((((int) (Main.widthFactor * 150)))), 2 * ((int) (Main.heightFactor * 25)));

                g.setColor(getCorrectColor("knight"));
                g.drawString("[3] Knight - $" + Knight.getUnitCost(), ((((int) (Main.widthFactor * 150)))), 3 * ((int) (Main.heightFactor * 25)));

                g.setColor(getCorrectColor("catapult"));
                g.drawString("[4] Catapult - $" + Catapult.getUnitCost(), ((((int) (Main.widthFactor * 150)))), 4 * ((int) (Main.heightFactor * 25)));

                //Unit Quick Info Items
                g.setColor(getCorrectColor("militia"));
                g.drawString("[1]", this.getWidth()-((int)(Main.widthFactor *425)), ((int)(Main.heightFactor *25)));

                g.setColor(getCorrectColor("infantry"));
                g.drawString("[2]", this.getWidth()-((int)(Main.widthFactor *425)), 2 * ((int)(Main.heightFactor *25)));

                g.setColor(getCorrectColor("knight"));
                g.drawString("[3]", this.getWidth()-((int)(Main.widthFactor *425)), 3 * ((int)(Main.heightFactor *25)));

                g.setColor(getCorrectColor("catapult"));
                g.drawString("[4]", this.getWidth()-((int)(Main.widthFactor *425)), 4 * ((int)(Main.heightFactor *25)));
            }
            else
            {
                //Information on Units
                g.setColor(getCorrectColor("cannon"));
                g.drawString("[1] Cannon - $" + Cannon.getUnitCost(), ((((int) (Main.widthFactor * 150)))), ((int) (Main.heightFactor * 25)));

                g.setColor(getCorrectColor("barricade"));
                g.drawString("[2] Barricade - $" + Cannon.getUnitCost(), ((((int) (Main.widthFactor * 150)))),2 * ((int) (Main.heightFactor * 25)));


                //Unit Quick Info Items
                g.setColor(getCorrectColor("cannon"));
                g.drawString("[1]", this.getWidth()-((int)(Main.widthFactor *425)), ((int)(Main.heightFactor *25)));

                g.setColor(getCorrectColor("barricade"));
                g.drawString("[2]", this.getWidth()-((int)(Main.widthFactor *425)), 2 * ((int)(Main.heightFactor *25)));
            }
        }
        else if (CastleDefenseBoard.showInfoMenu)
        {
            //Game Information
            g.setFont(normalFont);
            g.setColor(getCorrectColor(""));
            g.drawString("Castle Defense Created By:", 0, ((int)(Main.heightFactor *25)));
            g.drawString("Cody R.", 0, 2 * ((int)(Main.heightFactor *25)));
            g.drawString("Frank W.", 0, 3 * ((int)(Main.heightFactor *25)));

            //Controls
            g.drawString("Game Controls:", ((((int)(Main.widthFactor *500)))), ((int)(Main.heightFactor *25)));
            g.drawString("[1-9]: Spawn Units", ((((int)(Main.widthFactor *500)))), 2 * ((int)((Main.heightFactor *25))));
            g.drawString("[Arrow Keys]: Select Row/Column", ((((int)(Main.widthFactor *500)))), 3 * ((int)(Main.heightFactor *25)));
            g.drawString("[Esc]: Exit Game", ((((int)(Main.widthFactor *500)))), 4 * ((int)(Main.heightFactor *25)));
            g.drawString("[Space]: Pause Game", ((((int)(Main.widthFactor *500)))), 5 * ((int)(Main.heightFactor *25)));
            g.drawString("[Equals]: Zoom In", ((((int)(Main.widthFactor *1000)))), 1 * ((int)(Main.heightFactor *25)));
            g.drawString("[Minus]: Zoom Out", ((((int)(Main.widthFactor *1000)))), 2 * ((int)(Main.heightFactor *25)));
        }

        //This Is Displayed Regardless of Selected Menu
        g.setFont(normalFont);
        g.setColor(getCorrectColor(""));

        //Divider Between Unit Info And Other Info
        g.setColor(getCorrectColor(""));
        g.setFont(normalFont);
        g.fillRect(this.getWidth()-((int)(Main.widthFactor *325)),0,((int)(Main.widthFactor *20)), this.getHeight());

        //Economy Information
        g.drawString("Balance: $" + CastleDefense.getBalance(), this.getWidth()-((int)(Main.widthFactor *300)), ((int)(Main.heightFactor *25)));

        //Wave Information
        g.drawString("Wave: " + CastleDefense.getWave(), this.getWidth()-((int)(Main.widthFactor *300)), 2 * ((int)(Main.heightFactor *25)));

        //Remaining Lives
        g.drawString("Lives Remaining: " + CastleDefense.getLives(), this.getWidth()-((int)(Main.widthFactor *300)),  3 * ((int)(Main.heightFactor *25)));

        //High score
        g.drawString("High Score: " + CastleDefense.highestWave, this.getWidth()-((int)(Main.widthFactor *300)),  4 * ((int)(Main.heightFactor *25)));

    }

    private Color getCorrectColor(String unitType) {
        //String notReadyColor = "f45f42";
        Color notReadyColor = new Color(0xF45F42);
        Color readyColor = new Color(0x21D164);

        if (unitType.equalsIgnoreCase("militia")) {
            if (Militia.isReadyToSpawn() && Militia.COST <= CastleDefense.getBalance())
                return readyColor;
            else
                return notReadyColor;

        }
        if (unitType.equalsIgnoreCase("infantry")) {
            if (Infantry.isReadyToSpawn() && Infantry.COST <= CastleDefense.getBalance())
                return readyColor;
            else
                return notReadyColor;

        }
        if (unitType.equalsIgnoreCase("knight")) {
            if (Knight.isReadyToSpawn() && Knight.COST <= CastleDefense.getBalance())
                return readyColor;
            else
                return notReadyColor;

        }
        if (unitType.equalsIgnoreCase("catapult")) {
            if (Catapult.isReadyToSpawn() && Catapult.COST <= CastleDefense.getBalance())
                return readyColor;
            else
                return notReadyColor;
        }
        if (unitType.equalsIgnoreCase("cannon")) {
            if (Cannon.isReadyToSpawn() && Cannon.COST <= CastleDefense.getBalance())
                return readyColor;
            else
                return notReadyColor;
        }
        if (unitType.equalsIgnoreCase("barricade")) {
            if (Barricade.isReadyToSpawn() && Barricade.COST <= CastleDefense.getBalance())
                return readyColor;
            else
                return notReadyColor;
        }

        return Color.black;
    }

}
