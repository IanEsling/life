package life

import java.awt.Color
import life.board.Cell
import life.board.GroovyGameBoard
import life.board.GroovyGameCanvas
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 */

public class GroovyTestCellColours {

  GroovyGameBoard board;
  GroovyGameCanvas canvas;
  List<Cell> cellDiamond = new ArrayList<Cell>();

  @Before
  public void setUpGame() {
    board = new GroovyGameBoard(10, 10);
    canvas = new GroovyGameCanvas(board, 1);
  }

  @Before
  public void cellDiamond() {
    cellDiamond.add(new Cell(2, 2));
    cellDiamond.add(new Cell(3, 1));
    cellDiamond.add(new Cell(3, 3));
    cellDiamond.add(new Cell(4, 2));
  }

  @Test
  public void cellGoesRedWhenComesAlive() {
    setUpGame();
    setCellDiamondAlive();
    assertEquals("new alive cell should be green", Color.green.getRGB(), canvas.getImage().getRGB(1, 1));
  }

  private void setCellDiamondAlive() {
    for (Cell cell: cellDiamond) {
      setCellAlive(cell.getRow(), cell.getColumn());
    }
  }

  @Test
  public void cellGoesGreenAfterBeingAliveForTenTicks() {
    setCellDiamondAlive();
    tickFor(10);
    for (Cell cell: cellDiamond) {
      assertTrue("cell not alive after 10 turns", board.getCell(cell).isAlive());
      assertEquals("cell should go orange after being alive for 10 turns",
              Color.orange.getRGB(), canvas.getImage().getRGB(cell.getRow() - 1, cell.getColumn() - 1));
    }
    tickFor(100);
    for (Cell cell: cellDiamond) {
      assertTrue("cell not alive after 100 turns", board.getCell(cell).isAlive());
      assertEquals("cell should go red after being alive for 100 turns",
              Color.red.getRGB(), canvas.getImage().getRGB(cell.getRow() - 1, cell.getColumn() - 1));
    }
    tickFor(300);
    for (Cell cell: cellDiamond) {
      assertTrue("cell not alive after 10 turns", board.getCell(cell).isAlive());
      assertEquals("cell should go blue after being alive for 300 turns",
              Color.blue.getRGB(), canvas.getImage().getRGB(cell.getRow() - 1, cell.getColumn() - 1));
    }
  }

  private void tickFor(int ticks) {
    for (int i = 1; i <= ticks; i++) {
      board.tick();
    }
  }

  private void setCellAlive(int x, int y) {board.getCell(new Cell(x, y)).setAlive(true);}

}