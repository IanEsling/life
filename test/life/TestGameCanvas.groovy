package life

import java.awt.Color
import life.board.Cell
import life.board.GameBoard
import life.board.GameCanvas
import static org.junit.Assert.assertEquals
import org.junit.Test

/**
 */

public class TestGameCanvas {
  GameCanvas testee;

  @Test
  public void canvasHasImage() {
    testee = new GameCanvas(new GameBoard(10, 12), 1);
    assertEquals("canvas wrong width", 12, testee.getWidth());
    assertEquals("canvas wrong height", 10, testee.getHeight());
  }

  @Test
  public void canvasPixels() {
    GameBoard board = new GameBoard(10, 12);
    testee = new GameCanvas(board, 1);
    board.getCell(new Cell(2, 2)).setAlive(true);
    testee.setCellImages(board);
    (1..board.cells.size - 1).each
    {i ->
      if (i == 13) {
        assertEquals("pixel not turned on for cell at 2,2", Color.green.getRGB(), testee.getImage().getRGB(1, 1));
      }
      else {
        assertEquals("pixel not turned on for cell at " + board.getCells().get(i).toString(),
                Color.black.getRGB(),
                testee.getImage().getRGB(board.getCells().get(i).getColumn() - 1, board.getCells().get(i).getRow() - 1));
      }
    }
  }

}