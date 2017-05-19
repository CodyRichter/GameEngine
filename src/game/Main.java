package game;

import base.Board;
import base.GameGraphicsThread;
import base.GameOperationThread;
import game.enemies.Peasant;

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
    //Menu Variables
    public static JPanel menu;
    public static Thread graphics;
    public static Thread update;

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
        frame.setResizable(false);

        //Game JPanel Setup
        b = new CastleDefenseBoard();
        b.setBounds(0,menuSize+5,(int)width, (int)height-menuSize);
        //b.setSize((int) width, (int)height-menuSize);
        b.setPreferredSize(new Dimension((int)width,(int)height-menuSize));
        b.setLayout(null);
        b.setOpaque(true);
        b.setFocusable(true);
        b.requestFocus();

        //Menu JPanel Setup
        menu = new GameMenu();
        menu.setLayout(null);
        menu.setBounds(0,0,(int)width, menuSize);
        menu.setOpaque(true);
        menu.setPreferredSize(new Dimension((int)width,menuSize));

        //JPanel In JFrame That Hold JPanels - Prevents Screen From Flashing On Update
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(menu);
        panel.add(b);
        frame.add(panel);


        //Starts Runnable Thread
        graphics = new Thread(new GameGraphicsThread(b, menu));
        update = new Thread(new GameOperationThread(b,menu));
        graphics.start();
        update.start();

        //Sets The Frames Visible In Panel [ORDER MATTERS]
        frame.setVisible(true);
        menu.setVisible(true);

        //
        // Add Wave Behaviour Here!!!
        // V V V V V V V V V V V V


        for (int i = 1; i < 4; i++){
            Enemy e = new Peasant();
            e.spawn(i);
        }



    }

}


