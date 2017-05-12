package base;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Game base.Board That Is Displayed In Background
 * 
 * @author Cody Richter & Frank Williams
 * @version 1.0
 */
public class Board extends JPanel
{
    private List<Unit> unitList = new ArrayList<Unit>();

    Image background;
    public Board()
    {
        setBackgroundColor("42f448");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        /* Implementation Not Shown */
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        for(Unit u : unitList){
            g2d.drawImage(u.getSprite(),(int)u.getX(),(int)getY(),null);
        }
    }


    /*
    Sets Background Color
     */
    public void setBackgroundColor(String colorCode)
    {
        String color = "#" + colorCode;
        this.setBackground(Color.decode(color));
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
