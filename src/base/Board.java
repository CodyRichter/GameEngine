package base;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Game base.Board That Is Displayed In Background
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board extends JPanel
{
    private List<Unit> unitList = new ArrayList<Unit>();

    private double width;
    private double height;
    Image background;
    public Board(double inputWidth, double inputHeight)
    {
        width = inputWidth;
        height = inputHeight;
        background = Toolkit.getDefaultToolkit().createImage("src/background.jpeg");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        /* Implementation Not Shown */
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.decode("#42f448"));
        //g2d.drawImage(background, 0, 0, null);
        repaint();
    }

    /**
     * Adds Unit To Board
     * @param u
     */
    public void addUnit(Unit u)
    {
        unitList.add(u);
    }

    /**
     * Removes Unit From Board
     * @param u
     */
    public void removeUnit(Unit u)
    {
        unitList.remove(u);
    }


}
