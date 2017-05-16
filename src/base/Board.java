package base;

import game.CastleDefenseBoard;
import game.GameMenu;

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
    public List<Unit> unitList = new ArrayList<Unit>();

    Image background;
    public Board()
    {
        setBackgroundColor("42f448");
        //repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        /* Implementation Not Shown */
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        //
        // Displays The Rows That Units Will Move Down
        //

        g.setColor(GameMenu.getColor("ffffff"));

        //Row 1
        if (CastleDefenseBoard.selectedRow == 1) {g.setColor(GameMenu.getColor("abb9d1"));}
        else {g.setColor(GameMenu.getColor("ffffff"));}
        g.fillRect(0, this.getHeight()/8, 3000, 100);
        //Row 2
        if (CastleDefenseBoard.selectedRow == 2) {g.setColor(GameMenu.getColor("abb9d1"));}
        else {g.setColor(GameMenu.getColor("ffffff"));}
        g.fillRect(0, 3*this.getHeight()/8, 3000, 100);
        //Row 3
        if (CastleDefenseBoard.selectedRow == 3) {g.setColor(GameMenu.getColor("abb9d1"));}
        else {g.setColor(GameMenu.getColor("ffffff"));}
        g.fillRect(0, 5*this.getHeight()/8, 3000, 100);

        for(Unit u : unitList){
            g2d.drawImage(u.getSprite(),(int)u.getX(),(int)u.getY(),null);
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
        u.getSprite();
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

    /*
    Returns Units On Board
     */
    public List<Unit> getUnits()
    {
        return unitList;
    }


}
