package life;

import static junit.framework.Assert.*;
import life.board.*;
import org.junit.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 */
public class TestCellColours
{
    GameBoard board;
    GameCanvas canvas;
    List<Cell> cellDiamond = new ArrayList<Cell>();

    @Before
    public void setUpGame()
    {
        board = new GameBoard(10, 10);
        canvas = new GameCanvas(board, 1);
        canvas.addColourTransition(10, Color.orange);
        canvas.addColourTransition(100, Color.red);
        canvas.addColourTransition(300, Color.blue);
    }

    @Before
    public void cellDiamond()
    {
        cellDiamond.add(new Cell(2,2));
        cellDiamond.add(new Cell(3,1));
        cellDiamond.add(new Cell(3,3));
        cellDiamond.add(new Cell(4,2));
    }

    @Test
    public void cellGoesRedWhenComesAlive()
    {
        setUpGame();
        setCellDiamondAlive();
        assertEquals("new alive cell should be green", Color.green.getRGB(), canvas.getImage().getRGB(1, 1));
    }

    private void setCellDiamondAlive()
    {
        for (Cell cell : cellDiamond)
        {
            setCellAlive(cell.getRow(), cell.getColumn());
        }
    }

    @Test
    public void cellGoesGreenAfterBeingAliveForTenTicks()
    {
        setCellDiamondAlive();
        tickFor(10);
        for (Cell cell : cellDiamond)
        {
            assertTrue("cell not alive after 10 turns", board.getCell(cell).isAlive());
            assertEquals("cell should go orange after being alive for 10 turns",
                    Color.orange.getRGB(), canvas.getImage().getRGB(cell.getRow()-1, cell.getColumn()-1));
        }
        tickFor(100);
        for (Cell cell : cellDiamond)
        {
            assertTrue("cell not alive after 100 turns", board.getCell(cell).isAlive());
            assertEquals("cell should go red after being alive for 100 turns",
                    Color.red.getRGB(), canvas.getImage().getRGB(cell.getRow()-1, cell.getColumn()-1));
        }
        tickFor(300);
        for (Cell cell : cellDiamond)
        {
            assertTrue("cell not alive after 10 turns", board.getCell(cell).isAlive());
            assertEquals("cell should go blue after being alive for 300 turns",
                    Color.blue.getRGB(), canvas.getImage().getRGB(cell.getRow()-1, cell.getColumn()-1));
        }
    }

    private void tickFor(int ticks)
    {
        for (int i = 1; i <= ticks; i++)
        {
            board.tick();
        }
    }

    private void setCellAlive(int x, int y) {board.getCell(new Cell(x, y)).setAlive(true);}
}
