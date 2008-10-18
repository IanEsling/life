package life;

import life.cells.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Life
{
    GameBoard board;
    GameCanvas canvas;
    Button startButton, stopButton, randomise;
    JTextField generations;
    boolean gameIsRunning = false;
    GameRunner gameRunner = new GameRunner();
    Thread gameRunningThread;
    Integer numberOfTicks = 0;

    Life() throws InterruptedException
    {
        JFrame life = new JFrame();
        life.setLayout(new FlowLayout());

        createComponents();

        JPanel canvasPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        addCanvas(life, canvasPanel);
        addButtons(life, buttonPanel);

        setMainWindow(life, canvasPanel);

        setRandomCellsAlive(board);
        canvas.paint(canvas.getGraphics());
    }

    private void setMainWindow(JFrame life, JPanel canvasPanel)
    {
        life.setTitle("Game of Life");
        life.setSize(canvasPanel.getWidth() + 100, canvasPanel.getHeight() + 200);
        life.setVisible(true);
    }

    private void addButtons(JFrame life, JPanel buttonPanel)
    {
        addButtons(buttonPanel);

        life.add(buttonPanel);
    }

    private void addCanvas(JFrame life, JPanel canvasPanel)
    {
        canvasPanel.add(canvas);
        canvasPanel.setSize(canvas.getWidth(), canvas.getHeight());
        life.add(canvasPanel);
    }

    private void addButtons(JPanel buttonPanel)
    {
        buttonPanel.add(new JLabel("generations:"));
        buttonPanel.add(generations);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(randomise);
    }

    private void createComponents()
    {
        generations = new JTextField(numberOfTicks.toString());
        generations.setColumns(6);
        generations.setEditable(false);
        board = new GameBoard(300, 300);
        canvas = new GameCanvas(board, 2);
        startButton = new Button("start");
        stopButton = new Button("stop");
        randomise = new Button("randomise");
        randomise.addActionListener(new RandomiseListener());
        stopButton.addActionListener(new StopListener());
        startButton.addActionListener(new StartListener());
    }

    class GameRunner implements Runnable
    {
        public void run()
        {
            while (gameIsRunning)
            {
                board.tick();
                numberOfTicks++;
                generations.setText(numberOfTicks.toString());
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
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        new Life();
    }

    static void setRandomCellsAlive(GameBoard board)
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
        return board.getBoard().get((int)(Math.random() * board.getBoard().size()));
    }
}
