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
    JavaGameCanvas canvasJava;
    List<Cell> cellDiamond = new ArrayList<Cell>();

    @Before
    public void setUpGame()
    {
        board = new GameBoard(10, 10);
        canvasJava = new JavaGameCanvas(board, 1);
        canvasJava.addColourTransition(10, Color.orange);
        canvasJava.addColourTransition(100, Color.red);
        canvasJava.addColourTransition(300, Color.blue);
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
        assertEquals("new alive cell should be green", Color.green.getRGB(), canvasJava.getImage().getRGB(1, 1));
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
                    Color.orange.getRGB(), canvasJava.getImage().getRGB(cell.getRow()-1, cell.getColumn()-1));
        }
        tickFor(100);
        for (Cell cell : cellDiamond)
        {
            assertTrue("cell not alive after 100 turns", board.getCell(cell).isAlive());
            assertEquals("cell should go red after being alive for 100 turns",
                    Color.red.getRGB(), canvasJava.getImage().getRGB(cell.getRow()-1, cell.getColumn()-1));
        }
        tickFor(300);
        for (Cell cell : cellDiamond)
        {
            assertTrue("cell not alive after 10 turns", board.getCell(cell).isAlive());
            assertEquals("cell should go blue after being alive for 300 turns",
                    Color.blue.getRGB(), canvasJava.getImage().getRGB(cell.getRow()-1, cell.getColumn()-1));
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
