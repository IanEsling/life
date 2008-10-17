package life;

import static junit.framework.Assert.*;
import org.junit.*;

import java.awt.*;
import java.awt.image.*;

/**
 */
public class TestCellImage
{
    @Test
    public void imageUpdatesCanvas()
    {
        BufferedImage canvas = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Cell cell = new Cell(2, 2);
        CellImage ci = new CellImage(cell);
        ci.setImage(canvas);
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
}
