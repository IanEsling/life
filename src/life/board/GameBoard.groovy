package life.board

import life.rules.*

/**
 */

public class GameBoard {
  CellFactory cellFactory;
  List<Cell> board = new ArrayList<Cell>();
  int totalRows, totalColumns;
  public List<RuleHandler> rules = new ArrayList<RuleHandler>();
  List<Closure> tickListeners = new ArrayList<Closure>();
  public final static int defaultRows = 300, defaultColumns = 300;

  public GameBoard() {
    this(defaultRows, defaultColumns);
  }

  public GameBoard(int rows, int columns) {
    totalColumns = columns;
    totalRows = rows;
    cellFactory = new GroovyCellFactory(this);
    cellFactory.createGameBoardCells();
    setRules();
  }



  public void addTickListener(Closure c) {
    tickListeners.add(c);
  }

  protected void setRules() {
    setDieIfLessThanTwoLiveNeighboursRule();
    setDieIfMoreThanThreeLiveNeighboursRule();
    setComeToLifeIfExactlyThreeLiveNeighboursRule();
    setStayTheSameIfTwoOrThreeNeighboursRule();
  }

  public List<Cell> getCells() {
    return board;
  }

  public void tick() {
    board.each {cell -> applyRules(cell)}

    board.each {cell -> cell.applyNewState()}

    tickListeners.each {c -> c.call()}
  }

  void applyRules(Cell cell) {
    for (RuleHandler rule: rules) {
      if (rule.isEligible(cell)) {
        rule.applyRule(cell);
      }
    }
  }

  public Cell getCell(Cell cell) {
    if (!board.contains(cell)) {
      throw new CellNotFoundException(cell)
    }
    return board.get(board.indexOf(cell))
  }

  public void setDieIfLessThanTwoLiveNeighboursRule() {
    rules.add(new DieIfLessThanTwoLiveNeighboursRule())
  }

  public void setDieIfMoreThanThreeLiveNeighboursRule() {
    rules.add(new DieIfMoreThanThreeLiveNeighboursRule())
  }

  public void setComeToLifeIfExactlyThreeLiveNeighboursRule() {
    rules.add(new ComeToLifeIfExactlyThreeLiveNeighboursRule())
  }

  public void setStayTheSameIfTwoOrThreeNeighboursRule() {
    rules.add(new StayTheSameIfTwoOrThreeNeighboursRule())
  }
}