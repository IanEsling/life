package life;

import static junit.framework.Assert.*;
import org.junit.*;

import java.awt.*;
import java.awt.image.*;

/**
 */
public class TestCellImage
{
    BufferedImage canvas;
    Cell cell;

    @Before
    public void setUp()
    {
        canvas = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        cell = new Cell(2, 2);
    }

    @Test
    public void imageUpdatesCanvas()
    {
        new CellImage(new Cell(3, 3), canvas, 1);
        new CellImage(cell, canvas, 1);
        cell.setAlive(true);

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (i == 1 && j == 1)
                {
                    assertEquals("canvas pixel wrong at " + i + "," + j, Color.white.getRGB(), canvas.getRGB(i, j));
                }
                else
                {
                    assertEquals("canvas pixel wrong at " + i + "," + j, Color.black.getRGB(), canvas.getRGB(i, j));
                }
            }
        }
    }

    @Test
    public void updateMultiplePixels()
    {
        new CellImage(new Cell(3, 3), canvas, 2);
        new CellImage(cell, canvas, 2);
        cell.setAlive(true);
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if ((i == 1 || i == 2) && (j == 1 || j == 2))
                {
                    assertEquals("canvas pixel wrong at " + i + "," + j, Color.white.getRGB(), canvas.getRGB(i, j));
                }
                else
                {
                    assertEquals("canvas pixel wrong at " + i + "," + j, Color.black.getRGB(), canvas.getRGB(i, j));
                }
            }
        }
        cell.setAlive(false);
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                assertEquals("canvas pixel wrong at " + i + "," + j, Color.black.getRGB(), canvas.getRGB(i, j));
            }
        }
    }
}
