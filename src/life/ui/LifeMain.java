package life.ui;

import life.board.GroovyLifeGuiRenderer;

import javax.swing.*;
import java.awt.*;

/**
 */
public class LifeMain
{
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        JFrame app = new JFrame();
        app.setLayout(new FlowLayout());
        app.setSize(700,600);
        renderGameComponents(app);
        app.setTitle("Game Of Life");
    }

    private static void renderGameComponents(Container app)
    {
        GroovyLifeGuiRenderer guiRendererJava = new GroovyLifeGuiRenderer(app, new GroovyLifeRunner());
        guiRendererJava.setRandomCellsAlive();
        guiRendererJava.setMainWindow(app);
    }
}
