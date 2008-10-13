package life;

import static junit.framework.Assert.*;
import org.junit.*;

/**
 */
public class TestCell
{
    @Test
    public void cellKnowsWhereItIs()
    {
        Cell cell = new Cell(1, 2);
        assertEquals(1, cell.getRow());
        assertEquals(2, cell.getColumn());
    }

    @Test
    public void cellKnowsIfItIsAlive()
    {
        Cell cell = new Cell(10, 6);
        assertFalse(cell.isAlive());
        cell.setAlive(true);
        assertTrue(cell.isAlive());
    }

    @Test
    public void cellEquality()
    {
        Cell cellOne = new Cell(2, 4);
        Cell cellTwo = new Cell(2, 4);
        assertEquals(cellOne, cellTwo);
    }

    @Test
    public void cellListensToNeighbours()
    {
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);
        Cell cell3 = new Cell(1, 3);
        cell2.cellListener(cell1);
        cell2.cellListener(cell3);
        cell2.setAlive(true);
        assertEquals(1, cell1.getNumberOfLiveNeighbours());
        assertEquals(1, cell3.getNumberOfLiveNeighbours());
        cell2.setAlive(false);
        assertEquals(0, cell1.getNumberOfLiveNeighbours());
        assertEquals(0, cell3.getNumberOfLiveNeighbours());
    }
}
