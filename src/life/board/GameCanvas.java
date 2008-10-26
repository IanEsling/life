package life.board;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 */
public class GameCanvas extends Canvas
{
    GameBoard board;
    BufferedImage canvasImage;
    public static int pixelsSquarePerCell = 2;
    static Map<Integer, Color> colourTransitions = new HashMap<Integer, Color>();

    public void addColourTransition(Integer numberOfTicks, Color colour)
    {
        colourTransitions.put(numberOfTicks, colour);
    }

    public GameCanvas(GameBoard board, int pixelsSquarePerCell)
    {
        GameCanvas.pixelsSquarePerCell = pixelsSquarePerCell;
        setUp(board);
    }

    public GameCanvas(GameBoard board)
    {
        setUp(board);
    }

    private void setUp(GameBoard board)
    {
        this.board = board;
        addColourTransition(10, Color.orange);
        addColourTransition(100, Color.red);
        addColourTransition(300, Color.blue);
        setCanvasImage(board);
    }

    private void setCanvasImage(GameBoard board)
    {
        canvasImage = new BufferedImage(board.totalColumns*pixelsSquarePerCell,
                board.totalRows*pixelsSquarePerCell, BufferedImage.TYPE_INT_RGB);
        paint();
        setSize(canvasImage.getWidth(), canvasImage.getHeight());
        setBackground(Color.BLACK);
    }

    public BufferedImage getImage()
    {
        return canvasImage;
    }

    public void paint()
    {
        for (Cell cell : board.getCells())
        {
            new CellImage(cell, this);
        }
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(canvasImage, 0, 0, this);
    }
}
