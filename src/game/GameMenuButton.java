package game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Cody on 5/5/2017.
 */
public class GameMenuButton extends JPanel implements KeyListener {

    private static final int BOX_SIZE = 50;
    private Rectangle2D buttonBounds;
    private Point2D location;
    private String buttonType;


    public GameMenuButton(String button)
    {
        buttonType = button; //Sets Type of Button
        addKeyListener(this);
    }

    /**
     * Sets Button's Location On Board
     * @param point
     */
    public void setButtonLocation(Point2D point)
    {
        location = point;
    }

    public void useButton()
    {
        if (buttonType.equalsIgnoreCase("spawnEnemy"))
        {

        }
        else if (buttonType.equalsIgnoreCase("spawnFriendly"))
        {

        }
    }

    /*
    Will Run When A Key Is Pressed
     */
    public void keyPressed(KeyEvent e)
    {
     if (e.getKeyChar() == '1')
     {

     }
    }

    /*
    Will Run When A Key Is Released
     */
    public void keyReleased(KeyEvent e)
    {

    }

    public void keyTyped(KeyEvent e)
    {

    }
}
