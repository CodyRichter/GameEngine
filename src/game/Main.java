package game;

import base.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Runner Class
 * 
 * @author Cody Richter 
 * @version 1.0
 */
public class Main
{
    //Main Game Variables
    public static Board b;
    public static Graphics2D g;
    public static JFrame frame;
    public static JPanel field;
    public static JPanel enemy;
    public static Rectangle2D enemySpawn;
    public static Rectangle2D testSpawn;
    //Menu Variables
    public static JPanel menu;

    public static void main(String[] args)
    {
        /*
            Pre Game Setup Is Here
            This Will Configure The Main JFrame and the JPanels In It.
            Additionally, All Of The Menu Setup Will Be In Here.
         */

        //Initial Screen Size Information
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        int menuSize = (int)(height/8);
        //Main JFrame Setup
        frame = new JFrame("Castle Defense");
        frame.setSize((int)width, (int)height);
        frame.setFocusable(false);

        //Game JPanel Setup
        b = new CastleDefenseBoard();
        b.setBounds(0,menuSize+5,(int)width, (int)height-menuSize);
        //b.setSize((int) width, (int)height-menuSize);
        b.setPreferredSize(new Dimension((int)width,(int)height-menuSize));
        b.setLayout(null);
        b.setOpaque(true);
        b.setFocusable(true);
        b.requestFocus();


        enemySpawn = new Rectangle2D.Double(width,0,(width/2.0),height);
        testSpawn = new Rectangle2D.Double(0,0,width,height);

        //Menu JPanel Setup
        menu = new GameMenu();
        menu.setLayout(null);
        menu.setBounds(0,0,(int)width, menuSize);
        menu.setOpaque(true);
        menu.setPreferredSize(new Dimension((int)width,menuSize));

        //JPanel In JFrame That Hold JPanels
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(menu);
        panel.add(b);
        frame.add(panel);


        //Starts Runnable Thread
        new Thread(new base.GameThread(b, menu)).start();

        //Sets The Frames Visible In Panel [ORDER MATTERS]
        frame.setVisible(true);
        menu.setVisible(true);

    }
}


