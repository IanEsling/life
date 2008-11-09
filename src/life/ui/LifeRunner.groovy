package life.ui

import life.board.Cell
import life.board.CellListener
import life.board.GameBoard
import life.board.GameCanvas

/**
 */

public class LifeRunner {
  GameBoard board
  GameCanvas canvas
  Integer percentageOfBoardCells = 10
  Boolean gameIsRunning = false

  public LifeRunner() {
    board = new GameBoard()
    canvas = new GameCanvas(board)
  }


  void listenForBoardTicks(Closure c) {
    board.addTickListener(c);
  }

  void listenToAllTheGameCells(CellListener listener) {
    board.getCells().each {Cell cell ->
      cell.addCellListener(listener);
    }
  }

  void stopGame() {
    gameIsRunning = false;
  }

  void startGame() {
    gameIsRunning = true;
    Thread gameRunningThread = new Thread(new GameRunner(this));
    gameRunningThread.start();
  }

  void setRandomPercentageOfBoardCells(Integer value) {
    percentageOfBoardCells = value;
  }

  public void setRandomCellsAlive() {
    board.getCells().each {Cell cell ->
      cell.setAlive(false)
    }

    (1..((board.getCells().size() / 100) * percentageOfBoardCells)).each {
      boolean aliveCell = true;
      while (aliveCell) {
        Cell cell = getRandomCell();
        if (!cell.isAlive()) {
          cell.setAlive(true);
          aliveCell = false;
        }
      }
    }
  }

  private Cell getRandomCell() {
    return board.getCells().get((int) (Math.random() * board.getCells().size()));
  }
}

class GameRunner implements Runnable {

  LifeRunner runner

  GameRunner(LifeRunner runner) {
    this.runner = runner
  }

  public void run() {
    while (runner.gameIsRunning) {
      runner.board.tick();
      runner.canvas.paint(runner.canvas.getGraphics());
      try {
        Thread.sleep(25);
      }
      catch (InterruptedException e1) {
        e1.printStackTrace();
      }
    }
  }
}