package life.ui;

import javax.swing.*;
import java.awt.*;

/**
 */
public class LifeApplet extends JApplet
{
    JavaLifeGuiRenderer guiRendererJava;

    public LifeApplet() throws ClassNotFoundException, UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException
    {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        setLayout(new FlowLayout());
        renderGameComponents();
        setVisible(true);
    }

    private void renderGameComponents()
    {
        guiRendererJava = new JavaLifeGuiRenderer(this, new JavaLifeRunner());
        guiRendererJava.setRandomCellsAlive();
    }

    public void paint(Graphics g)
    {
        guiRendererJava.canvas().paint(g);
    }
}
