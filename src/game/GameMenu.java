package game;

import javax.swing.*;
import java.awt.*;

/**
 * Menu Displayed On Top Of Game Window. Dynamically Displays Information and Help to User.
 */
public class GameMenu extends JPanel{
    private Font font;
    private JLabel rowSelectInfo;
    private int currentRow = 0;

    public GameMenu()
    {
        super();

        //Sets Font For Text In Menu
        font = new Font("Verdana", Font.BOLD, 24);
        setFont(font);

    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        displayBoard(g);
    }

    private void displayBoard(Graphics g)
    {
        //Row Selection Control
        g.drawString("[1-3] Select Current Row | Current Row: " + CastleDefenseBoard.selectedRow, 0, 25);

        //Avaliable Units
        g.drawString("[M] Spawn Militia",0, 50);


    }

}
