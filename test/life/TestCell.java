package life;

import org.junit.*;
import static junit.framework.Assert.*;

import java.util.*;

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
}
