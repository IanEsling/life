package life.ui;

import life.board.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Life extends JApplet
{
    GameBoard board;
    GameCanvas canvas;
    public Button startButton, stopButton, randomise, closeButton;
    public JTextField generations;
    public CellListeningTextField liveCells;
    boolean gameIsRunning = false;
    GameRunner gameRunner = new GameRunner();
    Thread gameRunningThread;
    Integer numberOfTicks = 0, numberOfLiveCells = 0, numberOfDeadCells = 0;
    public final static int defaultRows = 300, defaultColumns = 300, pixelsPerSide = 2, mainWindowHeight = 500, mainWindowWidth = 500;

    public Life()
    {
        createComponents();
    }

    private void listenToAllTheGameCells(CellListener listener)
    {
        for (Cell cell : board.getBoard())
        {
            cell.addCellListener(listener);
        }
    }

    private void createComponents()
    {
        createTextFields();
        board = new GameBoard(defaultRows, defaultColumns);
        canvas = new GameCanvas(board, pixelsPerSide);
        createButtons();
        listenToAllTheGameCells(liveCells);
    }

    private void createButtons()
    {
        startButton = new Button("start");
        stopButton = new Button("stop");
        randomise = new Button("randomise");
        closeButton = new Button("exit");
        randomise.addActionListener(new RandomiseListener());
        stopButton.addActionListener(new StopListener());
        startButton.addActionListener(new StartListener());
        closeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {System.exit(0);}
        });
    }

    private void createTextFields()
    {
        generations = new JTextField(numberOfTicks.toString(), 6);
        liveCells = new CellListeningTextField(numberOfLiveCells.toString(), 6);
        generations.setEditable(false);
        liveCells.setEditable(false);
    }

    public void setLiveCellText()
    {
        liveCells.setText();
    }

    class GameRunner implements Runnable
    {
        @SuppressWarnings({"CallToPrintStackTrace"})
        public void run()
        {
            while (gameIsRunning)
            {
                board.tick();
                numberOfTicks++;
                generations.setText(numberOfTicks.toString());
                liveCells.setText();
                canvas.paint(canvas.getGraphics());
                try
                {
                    Thread.sleep(50);
                }
                catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    class StopListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            gameIsRunning = false;
        }
    }

    class StartListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            gameIsRunning = true;
            gameRunningThread = new Thread(gameRunner);
            gameRunningThread.start();
        }
    }

    class RandomiseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setRandomCellsAlive(board);
            canvas.paint(canvas.getGraphics());
            numberOfTicks = 0;
            liveCells.setText();
        }
    }

    public static void setRandomCellsAlive(GameBoard board)
    {
        for (Cell cell : board.getBoard())
        {
            cell.setAlive(false);
        }

        for (int i = 0; i < board.getBoard().size() / 10; i++)
        {
            getRandomCell(board).setAlive(true);
        }
    }

    private static Cell getRandomCell(GameBoard board)
    {
        return board.getBoard().get((int) (Math.random() * board.getBoard().size()));
    }

    class CellListeningTextField extends JTextField implements CellListener
    {
        CellListeningTextField(String text, Integer cols)
        {
            super(text, cols);
        }

        Integer total = 0;

        public void neighbourComeToLife() {total++;}

        public void neighbourHasDied() {total--;}

        public void setText()
        {
            setText(total.toString());
        }
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
