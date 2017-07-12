package game;

import base.Board;
import base.GameGraphicsThread;
import base.GameOperationThread;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

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
    public static Thread music;
    //Debug variables
    public static final boolean DEBUG = false;
    public static boolean VERBOSE = true;
    public static final boolean ADMINMODE = true;
    public static final boolean NOCOOLDOWN = true;
    public static final boolean ACTIONMODE = false;
    //Screen scaling stuff
    public static double screenWidth;
    public static double screenHeight;
    public static double widthFactor;
    public static double heightFactor;
    //why did I name everything factor
    public static final double[][] FACTORS = {{1440.0,900.0},{1920.0,1080.0},{2048.0,1152.0},{2880.0,1800.0},{3840.0,2400.0}};
    public static int factor = 1;

    public static File saveFile;
    public static String[] saveData;

    final static JFXPanel jfxPanel = new JFXPanel(); //to setup music stuff
    static MediaPlayer mediaPlayer;

    public static void main(String[] args)
    {
        /*
            Pre Game Setup Is Here
            This Will Configure The Main JFrame and the JPanels In It.
            Additionally, All Of The Menu Setup Will Be In Here.
         */


        //Initial Screen Size Information
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        screenHeight = screenSize.getHeight();

        widthFactor = (screenWidth/FACTORS[factor][0]);
        heightFactor = (screenHeight/FACTORS[factor][1]);


        System.out.println(screenHeight);
        int menuSize = (int)(screenHeight/8);

        //Main JFrame Setup
        if(VERBOSE) System.out.println("INITIAL JFrame SETUP");
        frame = new JFrame("Castle Defense");
        frame.setSize((int)screenWidth, (int)screenHeight);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        if(VERBOSE) System.out.println("JFrame Complete \n");

        //Game JPanel Setup
        if(VERBOSE) System.out.println("GAME BOARD SETUP");
        b = new CastleDefenseBoard();
        b.setBounds(0,menuSize+5,(int)screenWidth, (int)screenHeight-menuSize);
        //b.setSize((int) width, (int)height-menuSize);
        b.setPreferredSize(new Dimension((int)screenWidth,(int)screenHeight-menuSize));
        b.setLayout(null);
        b.setOpaque(true);
        b.setFocusable(true);
        b.requestFocus();
        if(VERBOSE) System.out.println("Board Complete \n");

        //Menu JPanel Setup
        if(VERBOSE) System.out.println("MENU SETUP");
        menu = new GameMenu();
        menu.setLayout(null);
        menu.setBounds(0,0,(int)screenWidth, menuSize);
        menu.setOpaque(true);
        menu.setPreferredSize(new Dimension((int)screenWidth,menuSize));
        if(VERBOSE) System.out.println("Menu Complete \n");

        //JPanel In JFrame That Hold JPanels - Prevents Screen From Flashing On Update
        if(VERBOSE) System.out.println("JPanel BoxLayout Setup - Initializing");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(menu);
        panel.add(b);
        frame.add(panel);
        if(VERBOSE) System.out.println("JPanel BoxLayout Setup - Complete!\n");


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

        //Setting up save
        try{
            saveFile = SaveEditor.createFile();
            saveData = SaveEditor.readFile(saveFile);

            //
            // Add Wave Behaviour Here!!!
            // V V V V V V V V V V V V
            CastleDefense.highestWave = Integer.parseInt(saveData[1]);
            if(!saveData[0].equals("T")) {
                CastleDefenseTutorial.main();
            } else {
                CastleDefense.tutorial = false;
                CastleDefense.nextWave();
            }
        } catch (Exception e){
            System.out.println("Save data failed, going to default " + e.getMessage());
            CastleDefenseTutorial.main();
        }
        if(VERBOSE) System.out.println("STARTING GAME\n\n");

        Media music = new Media(Main.class.getClass().getResource((ACTIONMODE ? "/game/sounds/ActionMode.mp3": "/game/sounds/Fake.mp3")).toString());
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.setStopTime(Duration.seconds(117));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }




}

