package life;

import static junit.framework.Assert.*;
import org.junit.*;

public class TestCellImage
{
    @Test
    public void cellImage()
    {
        Cell cell = new Cell(2, 3);
        CellImage cellImage = new CellImage(cell);
    }

    @Test
    public void canvasHasCellImageForEachGameCell()
    {
        GameBoard gameboard = new GameBoard(10, 10);
        GameCanvas canvas = new GameCanvas(gameboard);
        canvas.paint(canvas.getGraphics());
        assertEquals(canvas.cells.length, 10);
        assertEquals(canvas.cells[0].length, 10);
    }
}
