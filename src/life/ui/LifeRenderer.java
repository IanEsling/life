package life.ui;

import life.board.*;

import javax.swing.*;
import java.awt.*;

/**
 */
public class LifeRenderer
{
    Life life;
    JPanel canvasPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    public LifeRenderer(Life life)
    {
        this.life = life;
    }

    void addComponents(Container frame)
    {
        addCanvas(frame, canvasPanel);
        addButtons(frame, buttonPanel);
    }

    void setMainWindow(Container container)
    {
        container.setSize(canvasPanel.getWidth() + 100, canvasPanel.getHeight() + 200);
        container.setVisible(true);
    }

    private void addButtons(Container container, JPanel buttonPanel)
    {
        buttonPanel.add(new JLabel("generations:"));
        buttonPanel.add(life.generations);
        buttonPanel.add(new JLabel("live cells:"));
        buttonPanel.add(life.liveCells);
        buttonPanel.add(life.startButton);
        buttonPanel.add(life.stopButton);
        buttonPanel.add(life.randomise);
        buttonPanel.add(life.closeButton);

        container.add(buttonPanel);
    }

    private void addCanvas(Container container, JPanel canvasPanel)
    {
        canvasPanel.add(canvas());
        canvasPanel.setSize(canvas().getWidth(), canvas().getHeight());
        container.add(canvasPanel);
    }

    void setRandomCellsAlive()
    {
        Life.setRandomCellsAlive(life.getBoard());
        life.setLiveCellText();
    }

    GameCanvas canvas()
    {
        return life.getCanvas();
    }

    GameBoard getBoard()
    {
        return life.getBoard();
    }
}
