package base;

import java.awt.*;
import javax.swing.JPanel;
/**
 * Game base.Board That Is Displayed In Background
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board extends JPanel
{
    private double width;
    private double height;
    Image background;
    public Board(double inputWidth, double inputHeight)
    {
        width = inputWidth;
        height = inputHeight;
        background = Toolkit.getDefaultToolkit().createImage("src/background.jpeg");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.decode("#42f448"));
        //g2d.drawImage(background, 0, 0, null);
        repaint();
    }


}
