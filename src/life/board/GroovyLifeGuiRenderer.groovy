package life.board

import java.awt.*
import java.awt.event.ActionListener
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSlider
import javax.swing.JTextField
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener
import life.ui.GroovyLifeRunner

/**
 */

public class GroovyLifeGuiRenderer {
  GroovyLifeRunner lifeRunner;
  JPanel canvasPanel = new JPanel(new FlowLayout());
  JPanel controlPanel = new JPanel(new FlowLayout());
  JSlider slider;
  public Button startButton, stopButton, randomise, closeButton;
  public BoardListeningTextField generations;
  public CellListeningTextField liveCells;
  Integer numberOfTicks = 0, numberOfLiveCells = 0, numberOfDeadCells = 0;
  public final static int mainWindowHeight = 500, mainWindowWidth = 500, controlPanelWidth = 150,
  controlPanelHeight = 250;

  public GroovyLifeGuiRenderer(Container frame, GroovyLifeRunner lifeRunner) {
    this.lifeRunner = lifeRunner;
    createComponents();
    addComponents(frame);
  }

  void addComponents(Container frame) {
    addCanvas(frame, canvasPanel);
    setControlPanel(frame, controlPanel);
  }

  private void createComponents() {
    createTextFields();
    createButtons();
    createSlider();
    lifeRunner.listenToAllTheGameCells(liveCells);
    lifeRunner.listenForBoardTicks({generations.generations++; generations.setText()});
    lifeRunner.listenForBoardTicks({liveCells.setText()});
  }

  private void createSlider() {
    slider = new JSlider(0, 100, lifeRunner.percentageOfBoardCells);
    Properties labels = new Properties();
    labels.put(0, new JLabel("0%"));
    labels.put(50, new JLabel("50%"));
    labels.put(100, new JLabel("100%"));
    slider.setLabelTable(labels);
    slider.setPaintLabels(true);

    slider.addChangeListener(new SliderChangeListener(slider, lifeRunner, randomise));
  }

  private void randomiseCellsAndResetComponents() {
    lifeRunner.setRandomCellsAlive();
    canvas().repaint();
    liveCells.setText();
    generations.reset();
  }

  private void createButtons() {
    startButton = new Button("start")
    stopButton = new Button("stop")
    randomise = new Button("randomise")
    closeButton = new Button("exit")
    randomise.addActionListener({randomiseCellsAndResetComponents()} as ActionListener)
    stopButton.addActionListener({lifeRunner.stopGame()} as ActionListener)
    startButton.addActionListener({lifeRunner.startGame()} as ActionListener)
    closeButton.addActionListener({System.exit(0)} as ActionListener)
  }

  private void createTextFields() {
    generations = new BoardListeningTextField(numberOfTicks.toString(), 6);
    liveCells = new CellListeningTextField(numberOfLiveCells.toString(), 6);
    generations.setEditable(false);
    liveCells.setEditable(false);
  }

  void setMainWindow(Container container) {
    container.setSize(canvasPanel.getWidth() + 400, canvasPanel.getHeight() + 60);
    container.setVisible(true);
  }

  private void setControlPanel(Container container, JPanel controlPanel) {
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

  private void addCanvas(Container container, JPanel canvasPanel) {
    canvasPanel.setSize(canvas().getWidth(), canvas().getHeight());
    canvasPanel.add(canvas());
    container.add(canvasPanel);
  }

  void setRandomCellsAlive() {
    lifeRunner.setRandomCellsAlive();
    setLiveCellText();
  }

  GroovyGameCanvas canvas() {
    return lifeRunner.canvas
  }

  GroovyGameBoard getBoard() {
    return lifeRunner.board
  }

  private void setLiveCellText() {
    liveCells.setText();
  }
}

class BoardListeningTextField extends JTextField implements TickListener {
  Integer generations = 0;

  BoardListeningTextField(String text, Integer cols) {
    super(text, cols);
  }

  public void setText() {
    setText(generations.toString());
  }

  public void reset() {
    generations = 0;
    setText();
  }

  public void boardHasTicked() {
    generations++;
    setText();
  }
}

class CellListeningTextField extends JTextField implements CellListener, TickListener {
  Integer total = 0;

  CellListeningTextField(String text, Integer cols) {
    super(text, cols);
  }

  public void setText() {
    setText(total.toString());
  }

  public void listenedToCellHasComeToLife() {
    total++;
  }

  public void listenedToCellHasDied() {
    total--;
  }

  public void boardHasTicked() {
    setText();
  }
}

class SliderChangeListener implements ChangeListener {
  JSlider slider
  GroovyLifeRunner lifeRunner
  Button randomise

  SliderChangeListener(JSlider slider, GroovyLifeRunner lifeRunner, Button randomise) {
    this.slider = slider
    this.lifeRunner = lifeRunner
    this.randomise = randomise
  }

  public void stateChanged(ChangeEvent e) {
    if (!slider.getValueIsAdjusting()) {
      lifeRunner.setRandomPercentageOfBoardCells(slider.getValue());
      randomise.setLabel("randomise (" + slider.getValue() + "%)");
    }
  }
}