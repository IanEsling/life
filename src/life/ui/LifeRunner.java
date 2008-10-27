package life.ui;

import life.board.*;

public class LifeRunner
{
    private GameBoard board;
    private GameCanvas canvas;
    public Integer percentageOfBoardCells = 10;
    boolean gameIsRunning = false;
    GameRunner gameRunner = new GameRunner();
    Thread gameRunningThread;

    public LifeRunner()
    {
        board = new GameBoard();
        canvas = new GameCanvas(board);
    }

    void listenForBoardTicks(TickListener listener)
    {
        board.addTickListener(listener);
    }

    void listenToAllTheGameCells(CellListener listener)
    {
        for (Cell cell : board.getCells())
        {
            cell.addCellListener(listener);
        }
    }

    void stopGame()
    {
        gameIsRunning = false;
    }

    void startGame()
    {
        gameIsRunning = true;
        gameRunningThread = new Thread(gameRunner);
        gameRunningThread.start();
    }

    class GameRunner implements Runnable
    {
        @SuppressWarnings({"CallToPrintStackTrace"})
        public void run()
        {
            while (gameIsRunning)
            {
                board.tick();
                canvas.paint(canvas.getGraphics());
                try
                {
                    Thread.sleep(25);
                }
                catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    void setRandomPercentageOfBoardCells(Integer value)
    {
        percentageOfBoardCells = value;
    }

    public void setRandomCellsAlive()
    {
        for (Cell cell : board.getCells())
        {
            cell.setAlive(false);
        }

        for (int i = 0; i < (board.getCells().size() / 100) * percentageOfBoardCells; i++)
        {
            boolean aliveCell=true;
            while(aliveCell)
            {
                Cell cell = getRandomCell(board);
                if (!cell.isAlive())
                {
                    cell.setAlive(true);
                    aliveCell = false;
                }
            }                        
        }
    }

    private static Cell getRandomCell(GameBoard board)
    {
        return board.getCells().get((int) (Math.random() * board.getCells().size()));
    }

    public GameBoard getBoard()
    {
        return board;
    }

    public GameCanvas getCanvas()
    {
        return canvas;
    }
}
