package life.board;

import java.awt.*;
import java.awt.image.*;

/**
 */
public class GameCanvas extends Canvas
{
    GameBoard board;
    BufferedImage canvasImage;
    public static int pixelsSquarePerCell = 3;

    public GameCanvas(GameBoard board, int pixelsSquarePerCell)
    {
        GameCanvas.pixelsSquarePerCell = pixelsSquarePerCell;
        setUp(board);
    }

    GameCanvas(GameBoard board)
    {
        setUp(board);
    }

    private void setUp(GameBoard board)
    {
        this.board = board;
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
        for (Cell cell : board.getBoard())
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
