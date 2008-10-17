package life;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class Life
{
    public static void main(String[] args) throws InterruptedException
    {
        JFrame life = new JFrame();
        GameBoard board = new GameBoard(200,200);
        GameCanvas canvas = new GameCanvas(board, 3);
        life.add(canvas);
        life.setTitle("Game of Life");
        life.pack();
        life.setSize(canvas.getImage().getWidth(), canvas.getImage().getHeight());
        life.setVisible(true);
        setRandomCellsAlive(board);
        canvas.paint(canvas.getGraphics());
        while (true)
        {
            board.tick();
            canvas.repaint();
            Thread.sleep(100);
        }
    }

    static void setRandomCellsAlive(GameBoard board)
    {
        for (int i = 0; i < board.getBoard().size()/30;i++)
        {
            int cell = (int)(Math.random()*board.getBoard().size());
            System.out.println("setting cell number "+cell+" to alive");
            board.getBoard().get(cell).setAlive(true);
        }
    }
}
