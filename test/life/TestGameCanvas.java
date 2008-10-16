package life;

import static junit.framework.Assert.*;
import org.junit.*;

import java.awt.image.*;
import java.awt.*;

/**
 */
public class TestGameCanvas
{
    GameCanvas testee;

    @Test
    public void canvasHasImage()
    {
        testee = new GameCanvas(new GameBoard(10, 12), 1);
        assertEquals("canvas wrong width", 12, testee.getWidth());
        assertEquals("canvas wrong height", 10, testee.getHeight());
    }

    private Raster getRaster()
    {
        return testee.getImage().getRaster();
    }

    @Test
    public void canvasPixels()
    {
        GameBoard board = new GameBoard(10, 12);
        testee = new GameCanvas(board, 1);
        board.getCell(new Cell(2, 2)).setAlive(true);
        testee.paint();
        DataBuffer data = testee.getImage().getData(getRaster().getBounds()).getDataBuffer();
        for (int i = 0; i < data.getSize(); i++)
        {
            if (i == 13)
            {
                assertEquals("pixel not turned on at 2,2", new int[]{Color.white.getRGB()}, data.getElem(i));
            }
            else
            {
                assertEquals("image element wrong at " + i, 0, data.getElem(i));
            }
        }


    }
}
