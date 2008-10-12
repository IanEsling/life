package life;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

/**
 */
public class Life
{
    public static void main(String[] args)
    {
        JFrame life = new JFrame();
        life.add(getCanvas());
        life.setTitle("Game of Life");
        life.pack();        
        life.setSize(500, 500);
        life.setVisible(true);
    }

    private static Canvas getCanvas()
    {
        Canvas canvas = new Canvas(){
            public void paint(Graphics g)
            {
                Graphics2D g2d = (Graphics2D)g;
                BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
                bi.getGraphics().setColor(Color.WHITE);
                bi.getGraphics().fillRect(0, 0, 50, 50);
                g2d.drawImage(bi, 200, 200, this);
            }
        };
        canvas.setSize(500, 500);
        canvas.setBackground(Color.BLACK);
        return canvas;
    }
}
