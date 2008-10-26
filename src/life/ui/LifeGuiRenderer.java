package life.ui;

import life.board.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 */
public class LifeGuiRenderer
{
    LifeRunner lifeRunner;
    JPanel canvasPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JSlider slider;
    public Button startButton, stopButton, randomise, closeButton;
    public BoardListeningTextField generations;
    public CellListeningTextField liveCells;
    Integer numberOfTicks = 0, numberOfLiveCells = 0, numberOfDeadCells = 0;
    public final static int mainWindowHeight = 500, mainWindowWidth = 500;

    public LifeGuiRenderer(Container frame, LifeRunner lifeRunner)
    {
        this.lifeRunner = lifeRunner;
        createComponents();
        addComponents(frame);
    }

    void addComponents(Container frame)
    {
        addCanvas(frame, canvasPanel);
        addButtons(frame, controlPanel);
        addSlider(frame, controlPanel);
    }

    private void createComponents()
    {
        createTextFields();
        createButtons();
        createSlider();
        lifeRunner.listenToAllTheGameCells(liveCells);
        lifeRunner.listenForBoardTicks(generations);
        lifeRunner.listenForBoardTicks(liveCells);
    }

    private void createSlider()
    {
        slider = new JSlider(0, 100, 50);
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
        generations = new BoardListeningTextField(numberOfTicks.toString(), 6);
        liveCells = new CellListeningTextField(numberOfLiveCells.toString(), 6);
        generations.setEditable(false);
        liveCells.setEditable(false);
    }

    void addSlider(Container container, JPanel panel)
    {
        panel.add(slider);
    }

    void setMainWindow(Container container)
    {
        container.setSize(canvasPanel.getWidth() + 100, canvasPanel.getHeight() + 200);
        container.setVisible(true);
    }

    private void addButtons(Container container, JPanel buttonPanel)
    {
        buttonPanel.add(new JLabel("generations:"));
        buttonPanel.add(generations);
        buttonPanel.add(new JLabel("live cells:"));
        buttonPanel.add(liveCells);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(randomise);
        buttonPanel.add(closeButton);

        container.add(buttonPanel);
    }

    private void addCanvas(Container container, JPanel canvasPanel)
    {
        canvasPanel.add(canvas());
        canvasPanel.setSize(canvas().getWidth(), canvas().getHeight());
        container.add(canvasPanel);
    }

    void setRandomCellsAlive()
    {
        lifeRunner.setRandomCellsAlive();
        setLiveCellText();
    }

    GameCanvas canvas()
    {
        return lifeRunner.getCanvas();
    }

    GameBoard getBoard()
    {
        return lifeRunner.getBoard();
    }

    class BoardListeningTextField extends JTextField implements TickListener
    {
        Integer generations = 0;

        BoardListeningTextField(String text, Integer cols)
        {
            super(text, cols);
        }

        public void setText()
        {
            setText(generations.toString());
        }

        public void boardHasTicked()
        {
            generations++;
            setText();
        }
    }

    class CellListeningTextField extends JTextField implements CellListener, TickListener
    {
        CellListeningTextField(String text, Integer cols)
        {
            super(text, cols);
        }

        Integer total = 0;

        public void listenedToCellHasComeToLife() {total++;}

        public void listenedToCellHasDied() {total--;}

        public void setText()
        {
            setText(total.toString());
        }

        public void boardHasTicked()
        {
            setText();
        }
    }

    class StopListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            lifeRunner.stopGame();
        }
    }

    class StartListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            lifeRunner.startGame();
        }
    }

    class RandomiseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            lifeRunner.setRandomCellsAlive();
            numberOfTicks = 0;
            liveCells.setText();
        }
    }


    private void setLiveCellText()
    {
        liveCells.setText();
    }
}
