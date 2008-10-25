package life.ui;

import javax.swing.*;
import java.awt.*;

/**
 */
public class LifeApplet extends JApplet
{
    LifeRenderer renderer;
    
    public LifeApplet()
    {
        setLayout(new FlowLayout());
        renderGameComponents(this);
        setVisible(true);
    }

    private void renderGameComponents(Container app)
    {
        renderer = new LifeRenderer(new Life());
        renderer.addComponents(app);
        renderer.setRandomCellsAlive();
    }

    public void paint(Graphics g)
    {
        renderer.canvas().paint(g);
    }
}
