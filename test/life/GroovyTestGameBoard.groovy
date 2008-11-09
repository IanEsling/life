package life

import life.board.Cell
import life.board.GroovyGameBoard
import life.rules.RuleHandler
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*
import life.board.CellNotFoundException
import org.junit.Ignore

/**
 */

public class GroovyTestGameBoard {

  GroovyGameBoard gameboard;
  List<Cell> liveCells;
  int ticks = 0;

  private void setCell(int row, int column) {
    Cell cell = gameboard.getCell(new Cell(row, column));
    cell.setAlive(true);
    liveCells.add(cell);
  }

  @Before
  public void listOfLiveCellsToTest() {
    liveCells = new ArrayList<Cell>();
  }

  @Before
  public void newGameBoard() {
    gameboard = new RuleWatchingGameBoard(10, 10);
  }

  @Test
  public void testTickListener() {
    ticks = 0;
    gameboard.addTickListener(incrementTick)
    tickFor(10);
    assertEquals(10, ticks);
  }

  private void tickFor(int ticks) {
    (1..ticks).each {
      gameboard.tick();
    }
  }

  def incrementTick = {
    ticks++;
  }

  @Ignore
  //useful to see range of random cells chose
  public void randomCells() {
    List<Integer> results = new ArrayList<Integer>();
    gameboard = new GroovyGameBoard(100, 100);
    for (int i = 0; i < gameboard.getCells().size() / 23; i++) {
      int cell = (int) (Math.random() * gameboard.getCells().size());
      results.add(cell);
    }
    Collections.sort(results);
    System.out.println(results.toString());
  }

  @Test
  public void cellIsAliveOrDead() {
    assertWholeBoardIsDead();
    setCell(1, 2);
    assertOnlyCellAlive(1, 2);
  }

  @Test
  public void checkStateOfCellNeighbours() {
    setCell(1, 1);
    assertEquals("alive cell shouldn't count", 0, gameboard.getCell(new Cell(1, 1)).getNumberOfLiveNeighbours());
    assertEquals("1,2 should have 1 alive neighbour", 1, gameboard.getCell(new Cell(1, 2)).getNumberOfLiveNeighbours());
    assertEquals("2,2 should have 1 alive neighbour", 1, gameboard.getCell(new Cell(2, 2)).getNumberOfLiveNeighbours());
    assertEquals("2,1 should have 1 alive neighbour", 1, gameboard.getCell(new Cell(2, 1)).getNumberOfLiveNeighbours());
    assertEquals("2,3 should have 0 alive listeningCells", 0, gameboard.getCell(new Cell(2, 3)).getNumberOfLiveNeighbours());
    assertEquals("10,10 should have 0 alive listeningCells", 0, gameboard.getCell(new Cell(10, 10)).getNumberOfLiveNeighbours());
    setCell(1, 3);
    assertEquals("2,2 should have 2 alive listeningCells", 2, gameboard.getCell(new Cell(2, 2)).getNumberOfLiveNeighbours());
  }

  @Test (expected = CellNotFoundException.class)
  public void exceptionIfCellNotFound() {
    setCell(11, 11);
  }

  @Test
  public void cellWithFewerThanTwoLiveNeighboursDies() {
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
  public void cellWithMoreThanThreeLiveNeighboursDies() {
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

  @Test
  public void numberOfLiveNeighbours() {
    setCell(1, 1);
    setCell(1, 2);
    assertEquals("1,1 should have 1 neighbour", 1, gameboard.getCell(new Cell(1, 1)).getNumberOfLiveNeighbours());
    assertEquals("1,2 should have 1 neighbour", 1, gameboard.getCell(new Cell(1, 2)).getNumberOfLiveNeighbours());
    gameboard.tick();
    assertLiveCells();
    assertEquals("after 1 tick 1,1 should have 1 neighbour", 1, gameboard.getCell(new Cell(1, 1)).getNumberOfLiveNeighbours());
    assertEquals("after 1 tick 1,2 should have 1 neighbour", 1, gameboard.getCell(new Cell(1, 2)).getNumberOfLiveNeighbours());
    gameboard.tick();
    assertLiveCells();
    assertEquals("after 2 ticks 1,1 should have 1 neighbour", 1, gameboard.getCell(new Cell(1, 1)).getNumberOfLiveNeighbours());
    assertEquals("after 2 ticks 1,2 should have 1 neighbour", 1, gameboard.getCell(new Cell(1, 2)).getNumberOfLiveNeighbours());
  }

  private void assertLiveCells() {
    gameboard.getCells().each {Cell cell->
      if (liveCells.contains(cell)) {
        assertTrue("cell in liveCells not alive, row " + cell.getRow() + ", column " + cell.getColumn(),
                cell.isAlive());
      }
      else {
        assertFalse(cell.isAlive());
      }
    }
  }

  @Test
  public void cellWithTwoOrThreeLiveNeighboursAliveIsUnchanged() {
    gameboard.setDieIfLessThanTwoLiveNeighboursRule();
    gameboard.setStayTheSameIfTwoOrThreeNeighboursRule();
    setCell(1, 1);
    setCell(1, 2);
    setCell(1, 3);
    gameboard.tick();
    assertOnlyCellAlive(1, 2);
  }

  @Test
  public void cellWithExactlyThreeLiveNeighboursComesToLife() {
    gameboard.setComeToLifeIfExactlyThreeLiveNeighboursRule();
    setCell(1, 1);
    setCell(1, 3);
    setCell(3, 2);
    gameboard.tick();
    liveCells.add(new Cell(2, 2));
    assertLiveCells();
  }

  private void assertWholeBoardIsDead() {
    gameboard.getCells().each {Cell cell->
      assertEquals("expected cell " + cell.getRow() + ", " + cell.getColumn() + " to be false",
              false, cell.isAlive());
    }
  }

  private void assertOnlyCellAlive(int aliveRow, int aliveColumn) {
    gameboard.getCells().each {Cell cell->
      assertEquals("cell " + cell.getRow() + ", " + cell.getColumn() + " incorrect" +
              " when checking " + aliveRow + "," + aliveColumn,
              cell.isHere(aliveRow,aliveColumn), cell.isAlive());
    }
  }
}

class RuleWatchingGameBoard extends GroovyGameBoard {

  RuleWatchingGameBoard(int rows, int columns) {
    super(rows, columns);
  }

  @Override
  protected void setRules() {
    rules = new ArrayList<RuleHandler>();
  }
}