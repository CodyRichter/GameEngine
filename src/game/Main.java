package game;

import base.Board;

import javax.swing.*;
import java.awt.*;
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
    //Menu Variables
    public static JPanel menu;

    public static void main(String[] args)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        //Main JFrame Setup
        frame = new JFrame("Castle Defense");
        frame.setSize((int)width, (int)height);

        //Menu JPanel Setup
        menu = new JPanel();
        menu.setBounds(0,0,(int)width, 200);
        menu.setPreferredSize(new Dimension((int)width,200));
        menu.setLayout(null);

        //Game JPanel Setup
        b = new CastleDefenseBoard();
        b.setBounds(0,200,(int)width, (int)height-200);
        b.setPreferredSize(new Dimension((int)width,(int)height-200));
        b.setLayout(null);

        //Adds Panels To Frame
        frame.add(b);
        frame.add(menu);

        //Starts Runnable Thread
        new Thread(new base.GameThread(b)).start();

        frame.setVisible(true);
        menu.setVisible(true);
    }
}


