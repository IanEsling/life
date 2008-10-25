package life;

import static junit.framework.Assert.*;
import org.junit.*;

import java.awt.*;

import life.board.*;

/**
 */
public class TestCellImage
{
    GameCanvas canvas;
    GameBoard board;

    @Before
    public void setUp()
    {
        board = new GameBoard(10, 12);
    }

    @Test
    public void imageUpdatesCanvas()
    {
        canvas = new GameCanvas(board, 1);
        board.getBoard().get(board.getBoard().indexOf(new Cell(2,2))).setAlive(true);

        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (i == 1 && j == 1)
                {
                    assertEquals("canvas pixel wrong at " + i + "," + j, Color.white.getRGB(), canvas.getImage().getRGB(i, j));
                }
                else
                {
                    assertEquals("canvas pixel wrong at " + i + "," + j, Color.black.getRGB(), canvas.getImage().getRGB(i, j));
                }
            }
        }
    }

    @Test
    public void updateMultiplePixels()
    {
        canvas = new GameCanvas(board, 2);
        Cell cell1 = board.getBoard().get(board.getBoard().indexOf(new Cell(2,2)));
        Cell cell2 = board.getBoard().get(board.getBoard().indexOf(new Cell(9,9)));
        cell1.setAlive(true);
        cell2.setAlive(true);

        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (((i == 2 || i == 3) && (j == 2 || j == 3)) || ((i == 16 || i == 17) && (j == 16 || j == 17)))
                {
                    assertEquals("canvas pixel not white at " + i + "," + j, Color.white.getRGB(), canvas.getImage().getRGB(i, j));
                }
                else
                {
                    assertEquals("canvas pixel not black at " + i + "," + j, Color.black.getRGB(), canvas.getImage().getRGB(i, j));
                }
            }
        }
        
        cell1.setAlive(false);
        cell2.setAlive(false);
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                assertEquals("canvas pixel wrong at " + i + "," + j, Color.black.getRGB(), canvas.getImage().getRGB(i, j));
            }
        }
    }
}
