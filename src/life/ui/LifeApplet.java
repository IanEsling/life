package life.ui;

import javax.swing.*;
import java.awt.*;

/**
 */
public class LifeApplet extends JApplet
{
    LifeGuiRenderer guiRenderer;
    
    public LifeApplet()
    {
        setLayout(new FlowLayout());
        renderGameComponents();
        setVisible(true);
    }

    private void renderGameComponents()
    {
        guiRenderer = new LifeGuiRenderer(this, new LifeRunner());
        guiRenderer.setRandomCellsAlive();
    }

    public void paint(Graphics g)
    {
        guiRenderer.canvas().paint(g);
    }
}
