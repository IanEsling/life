package life.ui;

import life.board.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 */
public class LifeGuiRenderer
{
    LifeRunner lifeRunner;
    JPanel canvasPanel = new JPanel(new FlowLayout());
    JPanel controlPanel = new JPanel(new FlowLayout());
    JSlider slider;
    public Button startButton, stopButton, randomise, closeButton;
    public BoardListeningTextField generations;
    public CellListeningTextField liveCells;
    Integer numberOfTicks = 0, numberOfLiveCells = 0, numberOfDeadCells = 0;
    public final static int mainWindowHeight = 500, mainWindowWidth = 500, controlPanelWidth = 150,
            controlPanelHeight = 250;

    public LifeGuiRenderer(Container frame, LifeRunner lifeRunner)
    {
        this.lifeRunner = lifeRunner;
        createComponents();
        addComponents(frame);
    }

    void addComponents(Container frame)
    {
        addCanvas(frame, canvasPanel);
        setControlPanel(frame, controlPanel);
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
        slider = new JSlider(0, 100, lifeRunner.percentageOfBoardCells);
        Properties labels = new Properties();
        labels.put(0, new JLabel("0%"));
        labels.put(50, new JLabel("50%"));
        labels.put(100, new JLabel("100%"));
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                if (!slider.getValueIsAdjusting())
                {
                    lifeRunner.setRandomPercentageOfBoardCells(slider.getValue());
                    randomise.setLabel("randomise (" + slider.getValue() + "%)");
                }
            }
        });
    }

    private void randomiseCellsAndResetComponents()
    {
        lifeRunner.setRandomCellsAlive();
        canvas().repaint();
        liveCells.setText();
        generations.reset();
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

    void setMainWindow(Container container)
    {
        container.setSize(canvasPanel.getWidth() + 400, canvasPanel.getHeight() + 30);
        container.setVisible(true);
    }

    private void setControlPanel(Container container, JPanel controlPanel)
    {
        controlPanel.setPreferredSize(new Dimension(controlPanelWidth, controlPanelHeight));
        controlPanel.setLayout(new GridLayout(9, 1));

        controlPanel.add(new JLabel("generations:"));
        controlPanel.add(generations);
        controlPanel.add(new JLabel("live cells:"));
        controlPanel.add(liveCells);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(closeButton);
        controlPanel.add(randomise);
        controlPanel.add(slider);

        container.add(controlPanel);
    }

    private void addCanvas(Container container, JPanel canvasPanel)
    {
        canvasPanel.setSize(canvas().getWidth(), canvas().getHeight());
        canvasPanel.add(canvas());
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

        public void reset()
        {
            generations = 0;
            setText();
        }

        public void boardHasTicked()
        {
            generations++;
            setText();
        }
    }

    class CellListeningTextField extends JTextField implements CellListener, TickListener
    {
        Integer total = 0;

        CellListeningTextField(String text, Integer cols)
        {
            super(text, cols);
        }

        public void setText()
        {
            setText(total.toString());
        }

        public void listenedToCellHasComeToLife() {total++;}

        public void listenedToCellHasDied() {total--;}

        public void boardHasTicked() {setText();}
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
            randomiseCellsAndResetComponents();
        }
    }

    private void setLiveCellText()
    {
        liveCells.setText();
    }
}
