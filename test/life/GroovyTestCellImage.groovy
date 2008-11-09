package life

import life.board.Cell
import life.board.GroovyGameBoard
import life.board.GroovyGameCanvas
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*
import java.awt.Color

/**
 */

public class GroovyTestCellImage {

  GroovyGameCanvas canvasJava;
  GroovyGameBoard board;

  @Before
  public void setUp() {
    board = new GroovyGameBoard(10, 12);
  }

  @Test
  public void imageUpdatesCanvas() {
    canvasJava = new GroovyGameCanvas(board, 1);
    board.getCells().get(board.getCells().indexOf(new Cell(2, 2))).setAlive(true);

    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 10; j++) {
        if (i == 1 && j == 1) {
          assertEquals("canvasJava pixel wrong at " + i + "," + j, Color.green.getRGB(), canvasJava.getImage().getRGB(i, j));
        }
        else {
          assertEquals("canvasJava pixel wrong at " + i + "," + j, Color.black.getRGB(), canvasJava.getImage().getRGB(i, j));
        }
      }
    }
  }

  @Test
  public void updateMultiplePixels() {
    canvasJava = new GroovyGameCanvas(board, 2);
    Cell cell1 = board.getCells().get(board.getCells().indexOf(new Cell(2, 2)));
    Cell cell2 = board.getCells().get(board.getCells().indexOf(new Cell(9, 9)));
    cell1.setAlive(true);
    cell2.setAlive(true);

    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 10; j++) {
        if (((i == 2 || i == 3) && (j == 2 || j == 3)) || ((i == 16 || i == 17) && (j == 16 || j == 17))) {
          assertEquals("canvasJava pixel not white at " + i + "," + j, Color.green.getRGB(), canvasJava.getImage().getRGB(i, j));
        }
        else {
          assertEquals("canvasJava pixel not black at " + i + "," + j, Color.black.getRGB(), canvasJava.getImage().getRGB(i, j));
        }
      }
    }

    cell1.setAlive(false);
    cell2.setAlive(false);
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 10; j++) {
        assertEquals("canvasJava pixel wrong at " + i + "," + j, Color.black.getRGB(), canvasJava.getImage().getRGB(i, j));
      }
    }
  }

}