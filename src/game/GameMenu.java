package game;

import game.friendly.Catapult;
import game.friendly.Infantry;
import game.friendly.Knight;
import game.friendly.Militia;

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

        int fontSize = (int)(24 * (Main.screenHeight/1080));
        normalFont = new Font("Verdana", Font.BOLD, fontSize);
        titleFont = new Font("SansSerif", Font.ITALIC, 50);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayBoard(g);
    }

    private void displayBoard(Graphics g) {
        if (!CastleDefenseBoard.showUnitMenu && !CastleDefenseBoard.showInfoMenu) {

            //Shows Information On Each Unit Type
            g.setFont(normalFont);
            g.drawString("[U] Unit Information",0,((int)((Main.screenHeight/1080)*40)));
            g.drawString("[I] Game Information",0,((int)((Main.screenHeight/1080)*90)));

        } else if (CastleDefenseBoard.showUnitMenu) {
            g.setFont(normalFont);
            g.setColor(getCorrectColor(""));
            g.drawString("Avaliable", 0, ((int)((Main.screenHeight/1080)*50)));
            g.drawString("Units", 0, ((int)((Main.screenHeight/1080)*80)));
            g.fillRect((((int)((Main.screenWidth/1920)*130))),0,20, this.getHeight());

            //Avaliable Units
            g.setColor(getCorrectColor("militia"));
            g.drawString("[1] Militia - $" + Militia.getUnitCost(), ((((int)((Main.screenWidth/1920)*150)))), ((int)((Main.screenHeight/1080)*25)));

            g.setColor(getCorrectColor("infantry"));
            g.drawString("[2] Infantry - $" + Infantry.getUnitCost(), ((((int)((Main.screenWidth/1920)*150)))), 2* ((int)((Main.screenHeight/1080)*25)));

            g.setColor(getCorrectColor("knight"));
            g.drawString("[3] Knight - $" + Knight.getUnitCost(), ((((int)((Main.screenWidth/1920)*150)))), 3 * ((int)((Main.screenHeight/1080)*25)));

            g.setColor(getCorrectColor("catapult"));
            g.drawString("[4] Catapult - $" + Catapult.getUnitCost(), ((((int)((Main.screenWidth/1920)*150)))), 4 * ((int)((Main.screenHeight/1080)*25)));

        }
        else if (CastleDefenseBoard.showInfoMenu)
        {
            //Game Information
            g.setFont(normalFont);
            g.setColor(getCorrectColor(""));
            g.drawString("Castle Defense Created By:", 0, ((int)((Main.screenHeight/1080)*25)));
            g.drawString("Cody R.", 0, 2 * ((int)((Main.screenHeight/1080)*25)));
            g.drawString("Frank W.", 0, 3 * ((int)((Main.screenHeight/1080)*25)));

            //Controls
            g.drawString("Game Controls:", ((((int)((Main.screenWidth/1920)*500)))), ((int)((Main.screenHeight/1080)*25)));
            g.drawString("[1-9]: Spawn Units", ((((int)((Main.screenWidth/1920)*500)))), 2 * ((int)(((Main.screenHeight/1080)*25))));
            g.drawString("[Arrow Keys]: Select Row", ((((int)((Main.screenWidth/1920)*500)))), 3 * ((int)((Main.screenHeight/1080)*25)));
            g.drawString("[Esc]: Exit Game", ((((int)((Main.screenWidth/1920)*500)))), 4 * ((int)((Main.screenHeight/1080)*25)));
            g.drawString("[Space]: Pause Game", ((((int)((Main.screenWidth/1920)*500)))), 5 * ((int)((Main.screenHeight/1080)*25)));
        }

        //This Is Displayed Regardless of Selected Menu
        g.setFont(normalFont);
        g.setColor(getCorrectColor(""));

        //Unit Quick Info Items
        g.setColor(getCorrectColor("militia"));
        g.drawString("[1]", this.getWidth()-((int)((Main.screenWidth/1920)*425)), ((int)((Main.screenHeight/1080)*25)));

        g.setColor(getCorrectColor("infantry"));
        g.drawString("[2]", this.getWidth()-((int)((Main.screenWidth/1920)*425)), 2 * ((int)((Main.screenHeight/1080)*25)));

        g.setColor(getCorrectColor("knight"));
        g.drawString("[3]", this.getWidth()-((int)((Main.screenWidth/1920)*425)), 3 * ((int)((Main.screenHeight/1080)*25)));

        g.setColor(getCorrectColor("catapult"));
        g.drawString("[4]", this.getWidth()-((int)((Main.screenWidth/1920)*425)), 4 * ((int)((Main.screenHeight/1080)*25)));

        //Divider Between Unit Info And Other Info
        g.setColor(getCorrectColor(""));
        g.setFont(normalFont);
        g.fillRect(this.getWidth()-((int)((Main.screenWidth/1920)*325)),0,((int)((Main.screenWidth/1920)*20)), this.getHeight());

        //Economy Information
        g.drawString("Balance: $" + CastleDefense.getBalance(), this.getWidth()-((int)((Main.screenWidth/1920)*300)), ((int)((Main.screenHeight/1080)*25)));

        //Wave Information
        g.drawString("Wave: " + CastleDefense.getWave(), this.getWidth()-((int)((Main.screenWidth/1920)*300)), 2 * ((int)((Main.screenHeight/1080)*25)));

        //Remaining Lives
        g.drawString("Lives Remaining: " + CastleDefense.getLives(), this.getWidth()-((int)((Main.screenWidth/1920)*300)),  3 * ((int)((Main.screenHeight/1080)*25)));

    }


    public static Color getColor(String hexadecimal) {
        return Color.decode("#" + hexadecimal);
    }

    private Color getCorrectColor(String unitType) {
        String notReadyColor = "f45f42";
        String readyColor = "21d164";

        if (unitType.equalsIgnoreCase("militia")) {
            if (Militia.isReadyToSpawn() && Militia.COST <= CastleDefense.getBalance())
                return getColor(readyColor);
            else
                return getColor(notReadyColor);

        }
        if (unitType.equalsIgnoreCase("infantry")) {
            if (Infantry.isReadyToSpawn() && Infantry.COST <= CastleDefense.getBalance())
                return getColor(readyColor);
            else
                return getColor(notReadyColor);

        }
        if (unitType.equalsIgnoreCase("knight")) {
            if (Knight.isReadyToSpawn() && Knight.COST <= CastleDefense.getBalance())
                return getColor(readyColor);
            else
                return getColor(notReadyColor);

        }
        if (unitType.equalsIgnoreCase("catapult")) {
            if (Catapult.isReadyToSpawn() && Catapult.COST <= CastleDefense.getBalance())
                return getColor(readyColor);
            else
                return getColor(notReadyColor);
        }

        return getColor("000000");
    }

}
