package life;

import static junit.framework.Assert.*;
import org.junit.*;

import java.util.*;

/**
 */
public class TestGameBoard
{
    GameBoard gameboard;
    List<Cell> liveCells;

    private void setCell(int row, int column)
    {
        Cell cell = gameboard.getCell(row, column);
        cell.setAlive(true);
        liveCells.add(cell);
    }

    @Before
    public void listOfLiveCellsToTest()
    {
        liveCells = new ArrayList<Cell>();
    }

    @Before
    public void newGameBoard()
    {
        gameboard = new GameBoard(10, 10)
        {
            void setRules()
            {
                rules = new ArrayList<RuleHandler>();
            }
        };
    }

    @Test
    public void cellIsAliveOrDead()
    {
        assertWholeBoardIsDead();
        setCell(1, 2);
        assertOnlyCellAlive(1, 2);
    }

    @Test
    public void checkStateOfCellNeighbours()
    {
        setCell(1, 1);
        assertEquals("alive cell shouldn't count", 0, gameboard.getCell(1, 1).getNumberOfLiveNeighbours());
        assertEquals("1,2 should have 1 alive neighbour", 1, gameboard.getCell(1, 2).getNumberOfLiveNeighbours());
        assertEquals("2,2 should have 1 alive neighbour", 1, gameboard.getCell(2, 2).getNumberOfLiveNeighbours());
        assertEquals("2,1 should have 1 alive neighbour", 1, gameboard.getCell(2, 1).getNumberOfLiveNeighbours());
        assertEquals("2,3 should have 0 alive neighbours", 0, gameboard.getCell(2, 3).getNumberOfLiveNeighbours());
        assertEquals("10,10 should have 0 alive neighbours", 0, gameboard.getCell(10, 10).getNumberOfLiveNeighbours());
        setCell(1, 3);
        assertEquals("2,2 should have 2 alive neighbours", 2, gameboard.getCell(2, 2).getNumberOfLiveNeighbours());
    }

    @Test(expected = CellNotFoundException.class)
    public void exceptionIfCellNotFound()        
    {
        setCell(11, 11);
    }

    @Test
    public void cellWithFewerThanTwoLiveNeighboursDies()
    {
        gameboard.setDieIfLessThanTwoLiveNeighboursRule();
        setCell(1, 2);
        gameboard.tick();
        assertWholeBoardIsDead();
        setCell(1, 2);
        setCell(1, 4);
        setCell(2, 3);
        gameboard.tick();
        assertOnlyCellAlive(2, 3);
        setCell(1, 2);
        setCell(1, 4);
        setCell(3, 3);
        gameboard.tick();
        assertOnlyCellAlive(2, 3);
    }

    @Test
    public void cellWithMoreThanThreeLiveNeighboursDies()
    {
        gameboard.setDieIfMoreThanThreeLiveNeighboursRule();
        setCell(1, 1);
        setCell(1, 3);
        setCell(2, 2);
        setCell(3, 1);
        setCell(3, 3);
        gameboard.tick();
        liveCells.remove(new Cell(2, 2));
        assertLiveCells();
    }

    private void assertLiveCells()
    {
        for (Cell cell : gameboard.getBoard())
        {
            if (liveCells.contains(cell))
            {
                assertTrue("cell in liveCells not alive, row "+cell.getRow()+", column "+cell.getColumn(),
                        cell.isAlive());
            }
            else
            {
                assertFalse(cell.isAlive());
            }
        }
    }

    @Ignore
    public void cellWithTwoOrThreeLiveNeighboursAliveIsUnchanged()
    {
        setCell(1, 1);
        setCell(1, 2);
        setCell(1, 3);
        gameboard.tick();
        assertOnlyCellAlive(1, 2);
    }

    @Test
    public void cellWithExactlyThreeLiveNeighboursComesToLife()
    {
        gameboard.setComeToLifeIfExactlyThreeLiveNeighboursRule();
        setCell(1, 1);
        setCell(1, 3);
        setCell(3, 2);
        gameboard.tick();
        liveCells.add(new Cell(2, 2));
        assertLiveCells();
    }

    private void assertWholeBoardIsDead()
    {
        for (Cell cell : gameboard.getBoard())
        {
            assertEquals("expected cell " + cell.getRow() + ", " + cell.getColumn() + " to be false",
                    false, cell.isAlive());
        }
    }

    private void assertOnlyCellAlive(int aliveRow, int aliveColumn)
    {
        for (Cell cell : gameboard.getBoard())
        {
                assertEquals("cell " + cell.getRow() + ", " + cell.getColumn() + " incorrect" +
                        " when checking " + aliveRow + "," + aliveColumn,
                        cell.getRow() == aliveRow && cell.getColumn() == aliveColumn, cell.isAlive());
        }
    }

}
