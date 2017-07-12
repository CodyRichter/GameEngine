package base;

import game.CastleDefense;
import game.CastleDefenseBoard;
import game.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Game base.Board That Is Displayed In Background
 *
 * @author Cody Richter & Frank Williams
 * @version 1.0
 */
public class Board extends JPanel {
    public List<Unit> unitList = new CopyOnWriteArrayList<Unit>();
    protected String titleMessage = "";
    protected String notification = "";
    Color rowColor = new Color(0x75CE00);
    Color selectedColor = new Color(4, 68, 17);
    BufferedImage damage1; //Slightly Damaged Overlay
    BufferedImage damage2; //Moderately Damaged Overlay
    BufferedImage damage3; //Heavily Damaged Overlay


    Image background;

    public Board() {
        damage1 = makeBufferedImage("damage1.png");
        damage2 = makeBufferedImage("damage2.png");
        damage3 = makeBufferedImage("damage3.png");
        this.setBackground(new Color(0x42A000)); //Changed to color object so I could test IntelliJ's preview thing
        //repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        /* Implementation Not Shown */
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        //
        // Displays The Rows That Units Will Move Down
        //

        g.setColor(rowColor);

        //Paints Vertical Defense Placement Row
        if (CastleDefenseBoard.defensePlacementMode) {
            //Row Setup Checks
            int row;
            if (CastleDefenseBoard.selectedRow == 1) row = CastleDefense.ROW1Y;
            else if (CastleDefenseBoard.selectedRow == 2) row = CastleDefense.ROW2Y;
            else row = CastleDefense.ROW3Y;
            //Painting Normal Row
            g.setColor(rowColor);
            g.fillRect(0, CastleDefense.ROW1Y, 3000, (int) (Main.heightFactor * 100));
            g.fillRect(0, CastleDefense.ROW2Y, 3000, (int) (Main.heightFactor * 100));
            g.fillRect(0, CastleDefense.ROW3Y, 3000, (int) (Main.heightFactor * 100));
            //Paints Square To Indicate Turret Placement Area
            g.setColor(selectedColor);
            g.fillRect(CastleDefenseBoard.selectedColCoordinate, row, (int) (Main.heightFactor * 100), (int) (Main.heightFactor * 100));
            //Displays Indicator For Max Turret Placement Distance
            g.setColor(Color.BLACK);
            g.fillRect(CastleDefense.TURRET_PLACEMENT_LIMIT, CastleDefense.ROW1Y, (int) (Main.heightFactor * 20), (int) (Main.heightFactor * 100));
            g.fillRect(CastleDefense.TURRET_PLACEMENT_LIMIT, CastleDefense.ROW2Y, (int) (Main.heightFactor * 20), (int) (Main.heightFactor * 100));
            g.fillRect(CastleDefense.TURRET_PLACEMENT_LIMIT, CastleDefense.ROW3Y, (int) (Main.heightFactor * 20), (int) (Main.heightFactor * 100));


        } else
        //Paints Row 1 With Correct Color (Highlighted If Selected)
        {
            if (CastleDefenseBoard.selectedRow == 1) {
                g.setColor(selectedColor);
            } else {
                g.setColor(rowColor);
            }
            g.fillRect(0, CastleDefense.ROW1Y, 3000, (int) (Main.heightFactor * 100));
            //Paints Row 2 With Correct Color (Highlighted If Selected)
            if (CastleDefenseBoard.selectedRow == 2) {
                g.setColor(selectedColor);
            } else {
                g.setColor(rowColor);
            }
            g.fillRect(0, CastleDefense.ROW2Y, 3000, (int) (Main.heightFactor * 100));
            //Paints Row 3 With Correct Color (Highlighted If Selected)
            if (CastleDefenseBoard.selectedRow == 3) {
                g.setColor(selectedColor);
            } else {
                g.setColor(rowColor);
            }
            g.fillRect(0, CastleDefense.ROW3Y, 3000, (int) (Main.heightFactor * 100));
        }
        g.setColor(Color.black);
        g.setFont(new Font("SansSerif", Font.PLAIN, (int) (Main.widthFactor * 100)));
        g.drawString(titleMessage, 0, CastleDefense.ROW2Y);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, (int) (Main.heightFactor * 40)));
        g.drawString(notification, this.getWidth() / 2, this.getHeight() / 2);


        //
        // Game Over Code
        if (CastleDefense.gameOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.ITALIC, 100));
            g.drawString("Game Over!", this.getWidth() / 3, getHeight() / 3);
            return;
        }


        //
        //

        g.setColor(Color.BLACK);

        g.setFont(new Font("SansSerif", Font.ITALIC, (int) (Main.heightFactor * 15)));

        for (Unit u : unitList) {
            if (!u.isDead) {
                AffineTransformOp at = new AffineTransformOp(AffineTransform.getScaleInstance(Main.widthFactor, Main.heightFactor), null);

                //Displays Name Above Unit
                g2d.drawString(u.toString(), (int) u.getX(), (int) u.getY() - ((int) (Main.heightFactor * 12)));
                //Displays Health Bar Above Unit
                if (!u.isProjectile())
                    g2d.drawString("HP: " + (int) u.getCurrentHealth() + "/" + (int) u.getMaxHealth(), (int) u.getX(), (int) u.getY());
                //Displays Unit
                g2d.drawImage(u.getSprite(), at, (int) u.getX(), (int) u.getY());


                //Displays Damage To Unit Relative To Health
                if (u.getCurrentHealth() <= 3 * u.getMaxHealth() / 4 && u.getCurrentHealth() >= 2 * u.getMaxHealth() / 4)
                    g2d.drawImage(damage1, at, (int) u.getX(), (int) u.getY());
                else if (u.getCurrentHealth() <= 2 * u.getMaxHealth() / 4 && u.getCurrentHealth() >= u.getMaxHealth() / 4)
                    g2d.drawImage(damage2, at, (int) u.getX(), (int) u.getY());
                else if (u.getCurrentHealth() <= u.getMaxHealth() / 4)
                    g2d.drawImage(damage3, at, (int) u.getX(), (int) u.getY());
            }
        }


    }

    public void setTitleMessage(String msg) {
        titleMessage = msg;
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
     *
     * @param u
     */
    public void addUnit(Unit u) {
        unitList.add(u);
    }

    /**
     * Removes Unit From Board
     *
     * @param u
     */
    public void removeUnit(Unit u) {
        unitList.remove(u);
    }

    /*
    Returns Units On Board
     */
    public List<Unit> getUnits() {
        return unitList;
    }

    private boolean readyToSend = true;
    private List<String> messages = new ArrayList<>(); //Holds Messages To Send

    public void sendNotification(String message) {
        if (!messages.contains(message)) messages.add(message);

        if (readyToSend && messages.size() > 0) {
            readyToSend = false;
            notification = messages.get(0);
            java.util.Timer t = new java.util.Timer();

            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    notification = "";
                    readyToSend = true;
                    messages.remove(message);
                    if (messages.size() > 0) {
                        sendNotification(messages.get(0));
                    }

                }
            }, 1000);
        }
    }


}
