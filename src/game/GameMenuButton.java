package game;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Cody on 5/5/2017.
 */
public class GameMenuButton extends JPanel {

    private Rectangle2D buttonBounds;
    private Point2D location;
    private String buttonType;


    public GameMenuButton(String button)
    {
    buttonType = button; //Sets Type of Button
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


}
