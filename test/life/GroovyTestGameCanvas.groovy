package life

import life.board.GroovyGameCanvas
import org.junit.Test
import life.board.GroovyGameBoard
import life.board.Cell
import static org.junit.Assert.*
import java.awt.Color;

/**
 */

public class GroovyTestGameCanvas {
      GroovyGameCanvas testee;

    @Test
    public void canvasHasImage()
    {
        testee = new GroovyGameCanvas(new GroovyGameBoard(10, 12), 1);
        assertEquals("canvas wrong width", 12, testee.getWidth());
        assertEquals("canvas wrong height", 10, testee.getHeight());
    }

    @Test
    public void canvasPixels()
    {
        GroovyGameBoard board = new GroovyGameBoard(10, 12);
        testee = new GroovyGameCanvas(board, 1);
        board.getCell(new Cell(2, 2)).setAlive(true);
        testee.setCellImages(board);
        for (int i = 1; i < board.getCells().size(); i++)
        {
            if (i == 13)
            {
                assertEquals("pixel not turned on for cell at 2,2", Color.green.getRGB(), testee.getImage().getRGB(1,1));
            }
            else
            {
                assertEquals("pixel not turned on for cell at "+board.getCells().get(i).toString(),
                        Color.black.getRGB(),
                        testee.getImage().getRGB(board.getCells().get(i).getColumn()-1,board.getCells().get(i).getRow()-1));
            }
        }
    }

}