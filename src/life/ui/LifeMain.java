package life.ui;

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
        LifeGuiRenderer guiRenderer = new LifeGuiRenderer(app, new LifeRunner());
//        guiRenderer.addComponents(app);
        guiRenderer.setRandomCellsAlive();
        guiRenderer.setMainWindow(app);
    }
}
