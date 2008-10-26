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
        renderGameComponents(this);
        setVisible(true);
    }

    private void renderGameComponents(Container app)
    {
        guiRenderer = new LifeGuiRenderer(this, new LifeRunner());
        guiRenderer.addComponents(app);
        guiRenderer.setRandomCellsAlive();
    }

    public void paint(Graphics g)
    {
        guiRenderer.canvas().paint(g);
    }
}
