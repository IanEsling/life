package life

import life.board.Cell
import life.board.GroovyGameBoard
import life.ui.GroovyLifeRunner
import static org.junit.Assert.assertEquals
import org.junit.Test

/**
 */

public class GroovyTestRunner {
  GroovyLifeRunner testee;

  @Test
  public void liveCellCountWhenRandomising() {
    testee = new GroovyLifeRunner();
    testee.setRandomCellsAlive();
    assertEquals((int)((GroovyGameBoard.defaultRows * GroovyGameBoard.defaultColumns) / 100 * testee.percentageOfBoardCells),
            numberOfLiveCells());
  }

  int numberOfLiveCells() {
    int liveCells = 0;
    testee.board.cells.each {Cell cell->
      if (cell.isAlive()) liveCells++;
    }
    return liveCells;
  }

}