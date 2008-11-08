package life;

import org.junit.*;
import life.ui.*;
import life.board.*;
import static junit.framework.Assert.*;

/**
 */
public class TestRunner
{
    JavaLifeRunner testee;
    @Test
    public void liveCellCountWhenRandomising()
    {
        testee = new JavaLifeRunner();
        testee.setRandomCellsAlive();
        assertEquals(((GameBoard.defaultRows* GameBoard.defaultColumns)/100*testee.percentageOfBoardCells),
            numberOfLiveCells());
    }

    int numberOfLiveCells()
    {
        int liveCells = 0;
        for (Cell cell : testee.getBoard().getCells())
        {
            if (cell.isAlive()) liveCells++;
        }
        return liveCells;
    }
}
