import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Runner Class
 * 
 * @author Cody Richter 
 * @version 1.0
 */
public class Main
{
    public static Board b;
    public static Graphics2D g;
    public static JFrame frame;
    public static JPanel field;
    public static JPanel enemy;
    static int x = 100;
    static int y = 500;
    public static void main(String[] args)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        //JFrame Setup
        frame = new JFrame("Castle Defense");
        frame.setSize((int)width, (int)height);

        b = new Board(width, height);

        frame.add(b);
        new Thread(new GameThread(b)).start();

        frame.setVisible(true);
    }
}


