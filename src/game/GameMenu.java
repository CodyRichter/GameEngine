package game;

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
        g.drawString("Castle Defense", 800 , 100);

        //Row Selection Control
        g.setFont(normalFont);
        g.drawString("[1-3] Select Current Row | Current Row: " + CastleDefenseBoard.selectedRow, 0, 25);

        //Avaliable Units
        g.setColor(getCorrectColor("militia"));
        g.drawString("[M] Spawn Militia",0, 50);


    }

    private Color getColor(String hexadecimal)
    {
        return Color.decode("#"+hexadecimal);
    }

    private Color getCorrectColor(String unitType)
    {
        String notReadyColor = "f45f42";
        String readyColor = "21d164";

        if (unitType.equalsIgnoreCase("militia")) {
            if (Militia.getCooldown() > 0) {
                return getColor(notReadyColor);
            } else {
                return getColor(readyColor);
            }
        }
            return getColor("000000");
    }

}
