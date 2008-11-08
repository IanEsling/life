package life;

import static junit.framework.Assert.assertEquals;
import org.junit.*;

import java.awt.*;

import life.board.*;

/**
 */
public class TestGameCanvas
{
    JavaGameCanvas testee;

    @Test
    public void canvasHasImage()
    {
        testee = new JavaGameCanvas(new GameBoard(10, 12), 1);
        assertEquals("canvasJava wrong width", 12, testee.getWidth());
        assertEquals("canvasJava wrong height", 10, testee.getHeight());
    }

    @Test
    public void canvasPixels()
    {
        GameBoard board = new GameBoard(10, 12);
        testee = new JavaGameCanvas(board, 1);
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
