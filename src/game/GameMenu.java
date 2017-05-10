package game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Cody on 5/10/2017.
 */
public class GameMenu extends JPanel{
    Font font;

    public GameMenu()
    {
        super();
        font = new Font("Verdana", Font.BOLD, 24);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setFont(font);
        g.drawString("[1-3] Select Current Row",0,25);
        g.drawString("Currently Selected: " + CastleDefenseBoard.selectedRow, 0, 50);
    }


}
