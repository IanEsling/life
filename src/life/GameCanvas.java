package life;

import java.awt.*;
import java.awt.image.*;

/**
 */
public class GameCanvas extends Canvas
{
    GameBoard board;
    BufferedImage canvasImage;
    int pixelsSquarePerCell = 3;
    int[] whiteArray, blackArray;

    GameCanvas(GameBoard board, int pixelsSquarePerCell)
    {
        this.pixelsSquarePerCell = pixelsSquarePerCell;
        this.board = board;
        setCanvasImage(board);
    }

    GameCanvas(GameBoard board)
    {
        this.board = board;
        setCanvasImage(board);
    }

    private void setCanvasImage(GameBoard board)
    {
        canvasImage = new BufferedImage(board.totalColumns*pixelsSquarePerCell,
                board.totalRows*pixelsSquarePerCell, BufferedImage.TYPE_INT_RGB);
        whiteArray = rgbArray(Color.white);
        blackArray = rgbArray(Color.black);
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
            canvasImage.setRGB((cell.getColumn()-1) * pixelsSquarePerCell,
                    (cell.getRow() - 1) * pixelsSquarePerCell,
                    pixelsSquarePerCell, pixelsSquarePerCell,
                    cell.isAlive() ? whiteArray : blackArray,
                    0,0);
        }
    }

    private int[] rgbArray(Color colour)
    {
        int[] colours = new int[pixelsSquarePerCell*pixelsSquarePerCell];
        for (int i : colours)
        {
            colours[i] = colour.getRGB();
        }
        return colours;
    }

    public void paint(Graphics g)
    {
        paint();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(canvasImage, 0, 0, this);

    }
}
