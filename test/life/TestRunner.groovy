package life

import life.board.Cell
import life.board.GameBoard
import life.ui.LifeRunner
import static org.junit.Assert.assertEquals
import org.junit.Test
import life.board.GameBoard

/**
 */

public class TestRunner {
  LifeRunner testee;

  @Test
  public void liveCellCountWhenRandomising() {
    testee = new LifeRunner();
    testee.setRandomCellsAlive();
    assertEquals((int)((GameBoard.defaultRows * GameBoard.defaultColumns) / 100 * testee.percentageOfBoardCells),
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