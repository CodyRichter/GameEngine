package game;

import base.Board;
import base.GameGraphicsThread;
import base.GameOperationThread;

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
    //Debug variables
    public static final boolean DEBUG = false;
    public static final boolean VERBOSE = false;
    public static final boolean ADMINMODE = true;

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
        if(VERBOSE) System.out.println("INITIAL JFrame SETUP");
        frame = new JFrame("Castle Defense");
        frame.setSize((int)width, (int)height);
        frame.setFocusable(false);
        frame.setResizable(false);
        if(VERBOSE) System.out.println("JFrame Complete \n");

        //Game JPanel Setup
        if(VERBOSE) System.out.println("GAME BOARD SETUP");
        b = new CastleDefenseBoard();
        b.setBounds(0,menuSize+5,(int)width, (int)height-menuSize);
        //b.setSize((int) width, (int)height-menuSize);
        b.setPreferredSize(new Dimension((int)width,(int)height-menuSize));
        b.setLayout(null);
        b.setOpaque(true);
        b.setFocusable(true);
        b.requestFocus();
        if(VERBOSE) System.out.println("Board Complete \n");

        //Menu JPanel Setup
        if(VERBOSE) System.out.println("MENU SETUP");
        menu = new GameMenu();
        menu.setLayout(null);
        menu.setBounds(0,0,(int)width, menuSize);
        menu.setOpaque(true);
        menu.setPreferredSize(new Dimension((int)width,menuSize));
        if(VERBOSE) System.out.println("Menu Complete \n");

        //JPanel In JFrame That Hold JPanels - Prevents Screen From Flashing On Update
        if(VERBOSE) System.out.println("JPanel that does stuff");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(menu);
        panel.add(b);
        frame.add(panel);
        if(VERBOSE) System.out.println("Stuff? Complete\n");


        //Starts Runnable Thread
        if(VERBOSE) System.out.println("STARTING RUNNABLE THREADS\n");
        graphics = new Thread(new GameGraphicsThread(b, menu));
        update = new Thread(new GameOperationThread(b,menu));
        graphics.start();
        update.start();
        if(VERBOSE) System.out.println("THREADS STARTED\n");

        //Sets The Frames Visible In Panel [ORDER MATTERS]
        frame.setVisible(true);
        menu.setVisible(true);

        //
        // Add Wave Behaviour Here!!!
        // V V V V V V V V V V V V

        if(VERBOSE) System.out.println("STARTING GAME\n\n");
        CastleDefense.nextWave();

    }

}

