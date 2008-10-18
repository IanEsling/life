package life;

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

        canvasPanel.add(canvas);
        canvasPanel.setSize(canvas.getWidth(), canvas.getHeight());

        life.add(canvasPanel);

        addButtons(buttonPanel);

        life.add(buttonPanel);
        life.setTitle("Game of Life");
        life.setSize(500, 500);
        life.setVisible(true);
        setRandomCellsAlive(board);
        canvas.paint(canvas.getGraphics());
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

    private void refreshGenerations()
    {
        generations.setText(numberOfTicks.toString());
    }

    class GameRunner implements Runnable
    {
        public void run()
        {
            while (gameIsRunning)
            {
                board.tick();
                numberOfTicks++;
                refreshGenerations();
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
            int cell = (int) (Math.random() * board.getBoard().size());
            System.out.println("setting cell number " + cell + " to alive");
            board.getBoard().get(cell).setAlive(true);
        }
    }
}
