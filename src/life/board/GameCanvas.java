package life.board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class GameCanvas extends Canvas {
    BufferedImage canvasImage;
    public static int pixelsSquarePerCell = 2;
    static Map<Integer, Color> colourTransitions = new HashMap<Integer, Color>();

    public GameCanvas(GameBoard board, int pixelsSquarePerCell) {
        GameCanvas.pixelsSquarePerCell = pixelsSquarePerCell;
        setUp(board);
    }

    public GameCanvas(GameBoard board) {
        setUp(board);
    }

    private void setUp(GameBoard board) {
        addColourTransition(10, Color.orange);
        addColourTransition(100, Color.red);
        addColourTransition(300, Color.blue);
        setCanvasImage(board);
    }

    private void setCanvasImage(GameBoard board) {
        canvasImage = new BufferedImage(board.totalColumns * pixelsSquarePerCell,
                board.totalRows * pixelsSquarePerCell, BufferedImage.TYPE_INT_RGB);
        setCellImages(board);
        setSize(canvasImage.getWidth(), canvasImage.getHeight());
        setBackground(Color.BLACK);
    }

    public void setCellImages(GameBoard board) {
        for (Cell cell : board.getCells()) {
            new CellImage(cell, this, board);
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(canvasImage, 0, 0, this);
    }

    public BufferedImage getImage() {
        return canvasImage;
    }

    public void addColourTransition(Integer numberOfTicks, Color colour) {
        colourTransitions.put(numberOfTicks, colour);
    }
}
