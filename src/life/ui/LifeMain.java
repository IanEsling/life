package life.ui;

import life.*;

import javax.swing.*;
import java.awt.*;

/**
 */
public class LifeMain
{
    public static void main(String[] args) throws InterruptedException
    {
        JFrame app = new JFrame();
        app.setLayout(new FlowLayout());
        renderGameComponents(app);
        app.setTitle("Game Of Life");
    }

    private static void renderGameComponents(Container app)
    {
        LifeRenderer renderer = new LifeRenderer(new Life());
        renderer.addComponents(app);
        renderer.setRandomCellsAlive();
        renderer.setMainWindow(app);
    }
}
