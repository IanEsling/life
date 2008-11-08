package life.board

import java.awt.image.BufferedImage
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Color
import java.awt.Canvas

/**
 */

public class GroovyGameCanvas extends Canvas {
      BufferedImage canvasImage;
    public static int pixelsSquarePerCell = 2;
    def static colourTransitions = [10 : Color.orange, 100 : Color.red, 300 : Color.blue]

    public GroovyGameCanvas(GroovyGameBoard board) {
        setUp(board);
    }

    public GroovyGameCanvas(GroovyGameBoard board, int pixelsSquarePerCell) {
        GroovyGameCanvas.pixelsSquarePerCell = pixelsSquarePerCell;
        setUp(board);
    }

    private void setUp(GroovyGameBoard board) {
        setCanvasImage(board);
    }

    private void setCanvasImage(GroovyGameBoard board) {
        canvasImage = new BufferedImage(board.totalColumns * pixelsSquarePerCell,
                board.totalRows * pixelsSquarePerCell, BufferedImage.TYPE_INT_RGB);
        setCellImages(board);
        setSize(canvasImage.getWidth(), canvasImage.getHeight());
        setBackground(Color.BLACK);
    }

    public void setCellImages(GroovyGameBoard board) {
      board.getCells().each {cell->
        new GroovyCellImage(cell, this, board)
      }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(canvasImage, 0, 0, this);
    }

    public BufferedImage getImage() {
        return canvasImage;
    }
}