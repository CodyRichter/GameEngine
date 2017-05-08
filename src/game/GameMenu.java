package game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Cody on 5/5/2017.
 */
public class GameMenu extends JPanel {

    private GameMenuButton[] boxes;


    public GameMenu(int size)//should the menu size be a double?
    {
        boxes = new GameMenuButton[size];

    }

    /**
     * Adds a Game Menu Button to a Box on the Menu
     * @param button
     * @param box
     */
    public void addButtonToBox(GameMenuButton button, int box)
    {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        repaint();
    }

}
