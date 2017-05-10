package game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Cody on 5/10/2017.
 */
public class GameMenu extends JPanel{
    private Font font;
    private JLabel rowSelectInfo;


    public GameMenu()
    {
        super();

        //Sets Font For Text In Menu
        font = new Font("Verdana", Font.BOLD, 24);

        //Sets Layout That Contains Text
        GridLayout layout = new GridLayout(3,3);
        setLayout(layout);

        //Adds Label Holding The Text
        rowSelectInfo = new JLabel();
        labelSetup(rowSelectInfo, 0, 0);
        rowSelectInfo.setText("[1-3] Select Current Row");
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //g.setFont(font);
        //g.drawString("[1-3] Select Current Row",0,25);
        //g.drawString("Currently Selected: " + CastleDefenseBoard.selectedRow, 0, 50);
    }

    private void labelSetup(JLabel l, int x, int y)
    {
        l.setLocation(0+font.getSize(),0); //Sets Coordinates Of Label, Taking Into Account Font Size
        l.setFont(font);
        l.setText("[1-3] Select Current Row");
        l.setFocusable(false);
        l.setOpaque(true);
        l.setVerticalTextPosition(JLabel.TOP);
        l.setHorizontalTextPosition(JLabel.CENTER);
        add(l);
        l.setVisible(true);
    }

}
