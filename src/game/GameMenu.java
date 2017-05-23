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
public class GameMenu extends JPanel{
    private Font normalFont;
    private Font titleFont;
    private JLabel rowSelectInfo;
    private int currentRow = 0;

    public GameMenu()
    {
        super();
        //this.setBackground(Color.decode("#42f448"));
        //Sets Font For Text In Menu
        normalFont = new Font("Verdana", Font.BOLD, 24);
        titleFont = new Font("SansSerif", Font.ITALIC, 50);

    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        displayBoard(g);
    }

    private void displayBoard(Graphics g)
    {
        //Game Title Information
        g.setFont(titleFont);
        g.setColor(getCorrectColor(""));
        g.drawString("Castle Defense", this.getWidth()/2 , 2*(this.getHeight()/3));

        //Row Selection Control
        g.setFont(normalFont);
        g.drawString("[1-3] Select Current Row | Current Row: " + CastleDefenseBoard.selectedRow, 0, 25);

        //Economy Information
        g.drawString("Balance: " + CastleDefense.getBalance(), this.getWidth()-300, 25);

        //Wave Information
        g.drawString("Wave: " + CastleDefense.getWave(), this.getWidth()-300, 50);

        //Avaliable Units
        g.setColor(getCorrectColor("militia"));
        g.drawString("[M] Spawn Militia - $50",0, 50);

        g.setColor(getCorrectColor("infantry"));
        g.drawString("[I] Spawn Infantry - $200",0, 75);

        g.setColor(getCorrectColor("knight"));
        g.drawString("[K] Spawn Knight - $350",0, 100);

        g.setColor(getCorrectColor("catapult"));
        g.drawString("[C] Spawn Catapult - $1000",360, 50);

    }

    public static Color getColor(String hexadecimal)
    {
        return Color.decode("#"+hexadecimal);
    }

    private Color getCorrectColor(String unitType)
    {
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
        if(unitType.equalsIgnoreCase("catapult")) {
            if (Catapult.isReadyToSpawn() && Catapult.COST <= CastleDefense.getBalance())
                return getColor(readyColor);
            else
                return getColor(notReadyColor);
        }

            return getColor("000000");
    }

}
