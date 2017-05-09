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
    //Menu Variables
    public static JPanel menu;
    public static JPanel button1;

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

        //Game JPanel Setup
        b = new CastleDefenseBoard();
        b.setBounds(0,menuSize,(int)width, (int)height-menuSize);
        b.setPreferredSize(new Dimension((int)width,(int)height-menuSize));
        b.setLayout(null);

        enemySpawn = new Rectangle2D.Double(width,0,(width/2.0),height);

        //Menu JPanel Setup
        menu = new JPanel();
        menu.setBounds(0,0,(int)width, menuSize);
        menu.setPreferredSize(new Dimension((int)width,menuSize));
        menu.setLayout(null);

        //Menu Button JPanel Setup
        int buttonWidth = (int)(width/8);
        button1 = new JPanel();
        button1.setBackground(Color.decode("#000000"));
        button1.setBounds(0,0,buttonWidth, menuSize);
        button1.setPreferredSize(new Dimension(buttonWidth,menuSize));
        button1.setLayout(new BorderLayout());
        menu.add(button1, BorderLayout.EAST);

        //Adds Panels To Frame
        frame.add(b);
        frame.add(menu);



        //Starts Runnable Thread
        new Thread(new base.GameThread(b)).start();

        //Sets The Frames Visible In Panel [ORDER MATTERS]
        frame.setVisible(true);
        menu.setVisible(true);
        button1.setVisible(true);
    }
}


