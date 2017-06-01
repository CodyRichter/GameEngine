package base;

import game.CastleDefense;
import game.CastleDefenseBoard;
import game.GameMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Game base.Board That Is Displayed In Background
 *
 * @author Cody Richter & Frank Williams
 * @version 1.0
 */
public class Board extends JPanel
{
    public List<Unit> unitList = new CopyOnWriteArrayList<Unit>();
    protected String message = "";
    BufferedImage damage1;
    BufferedImage damage2;
    BufferedImage damage3;


    Image background;
    public Board()
    {
        damage1 = makeBufferedImage("damage1.png");
        damage2 = makeBufferedImage("damage2.png");
        damage3 = makeBufferedImage("damage3.png");
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

        //Paints Row 1 With Correct Color (Highlighted If Selected)
        if (CastleDefenseBoard.selectedRow == 1) {g.setColor(GameMenu.getColor("abb9d1"));}
        else {g.setColor(GameMenu.getColor("ffffff"));}
        g.fillRect(0, CastleDefense.ROW1Y, 3000, 100);
        //Paints Row 2 With Correct Color (Highlighted If Selected)
        if (CastleDefenseBoard.selectedRow == 2) {g.setColor(GameMenu.getColor("abb9d1"));}
        else {g.setColor(GameMenu.getColor("ffffff"));}
        g.fillRect(0, CastleDefense.ROW2Y, 3000, 100);
        //Paints Row 3 With Correct Color (Highlighted If Selected)
        if (CastleDefenseBoard.selectedRow == 3) {g.setColor(GameMenu.getColor("abb9d1"));}
        else {g.setColor(GameMenu.getColor("ffffff"));}
        g.fillRect(0, CastleDefense.ROW3Y, 3000, 100);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 100));
        g.drawString(message,0,CastleDefense.ROW2Y);

        //
        // Game Over Code
        if (CastleDefense.gameOver)
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.ITALIC, 100));
            g.drawString("Game Over!", this.getWidth()/3,getHeight()/3);
            return;
        }


        //
        //

        g.setColor(Color.BLACK);

        g.setFont(new Font("SansSerif", Font.ITALIC, 15));

        for(Unit u : unitList) {
            if (!u.isDead){

                //Displays Name Above Unit
                g2d.drawString(u.toString(), (int)u.getX(), (int)u.getY()-12);
                //Displays Health Bar Above Unit
                if (!u.isProjectile())
                g2d.drawString("HP: " + (int) u.getCurrentHealth() + "/" + (int) u.getMaxHealth(), (int) u.getX(), (int) u.getY());
                //Displays Unit
                g2d.drawImage(u.getSprite(), (int) u.getX(), (int) u.getY(), null);


                //Displays Damage To Unit Relative To Health
                if (u.getCurrentHealth() < 3*u.getMaxHealth()/4 && u.getCurrentHealth() > 2*u.getMaxHealth()/4)
                    g2d.drawImage(damage1, (int) u.getX(), (int) u.getY(), null);
                else if (u.getCurrentHealth() < 2*u.getMaxHealth()/4 && u.getCurrentHealth() > u.getMaxHealth()/4)
                    g2d.drawImage(damage2, (int) u.getX(), (int) u.getY(), null);
                else if (u.getCurrentHealth() < u.getMaxHealth()/4)
                    g2d.drawImage(damage3, (int) u.getX(), (int) u.getY(), null);
            }
        }


    }

    public void setMessage(String msg){
        message = msg;
    }



    /*
    Sets Background Color
     */
    public void setBackgroundColor(String colorCode)
    {
        String color = "#" + colorCode;
        this.setBackground(Color.decode(color));
    }



    /*
    Creates A Buffered Image From Input Location. Include File Type.
     */
    public BufferedImage makeBufferedImage(String fileName) {
        InputStream in = getClass().getResourceAsStream("/game/images/" + fileName);
        try {
            return ImageIO.read(in);
        } catch (IOException ioe) {
            System.out.println("IO Exception when setting sprite: " + ioe.getMessage());
        } catch (IllegalArgumentException iae) {
            System.out.println("IllegalArgumentException when setting sprite: " + iae.getMessage());
        }
        return null; //Error If File Is Invalid
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
    public void removeUnit(Unit u) {unitList.remove(u);}

    /*
    Returns Units On Board
     */
    public List<Unit> getUnits()
    {
        return unitList;
    }


}
