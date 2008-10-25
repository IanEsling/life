package life;

import javax.swing.*;
import java.awt.*;

/**
 */
public class LifeApplet extends JApplet
{
    Life game;
    
    public LifeApplet()
    {
        setLayout(new FlowLayout());
        setSize(Life.mainWindowWidth, Life.mainWindowHeight);
        setVisible(true);

        game = new Life();
        game.addComponents(this);
    }

    public void paint(Graphics g)
    {
        game.canvas.paint(g);
    }
}
