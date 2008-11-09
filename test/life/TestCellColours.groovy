package life

import java.awt.Color
import life.board.Cell
import life.board.GameBoard
import life.board.GameCanvas
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import life.board.GameCanvas

/**
 */

public class TestCellColours {

  GameBoard board;
  GameCanvas canvas;
  List<Cell> cellDiamond = new ArrayList<Cell>();

  @Before
  public void setUpGame() {
    board = new GameBoard(10, 10);
    canvas = new GameCanvas(board, 1);
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
    cellDiamond.each {Cell cell ->
      setCellAlive(cell);
    }
  }

  @Test
  public void cellGoesGreenAfterBeingAliveForTenTicks() {
    setCellDiamondAlive();
    tickFor(10);
    cellDiamond.each {Cell cell ->
      assertTrue("cell not alive after 10 turns", board.getCell(cell).isAlive());
      assertEquals("cell should go orange after being alive for 10 turns",
              Color.orange.getRGB(), canvas.getImage().getRGB(cell.getRow() - 1, cell.getColumn() - 1));
    }
    tickFor(100);
    cellDiamond.each {Cell cell ->
      assertTrue("cell not alive after 100 turns", board.getCell(cell).isAlive());
      assertEquals("cell should go red after being alive for 100 turns",
              Color.red.getRGB(), canvas.getImage().getRGB(cell.getRow() - 1, cell.getColumn() - 1));
    }
    tickFor(300);
    cellDiamond.each {Cell cell ->
      assertTrue("cell not alive after 10 turns", board.getCell(cell).isAlive());
      assertEquals("cell should go blue after being alive for 300 turns",
              Color.blue.getRGB(), canvas.getImage().getRGB(cell.getRow() - 1, cell.getColumn() - 1));
    }
  }

  def tickFor = {ticks ->
    (1..ticks).each {
      board.tick();
    }
  }

  def setCellAlive = {Cell cell -> board.getCell(cell).setAlive(true)}
}