package life.board

import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage

/**
 */

public class GameCanvas extends Canvas {
  BufferedImage canvasImage;
  public static int pixelsSquarePerCell = 2;
  def static colourTransitions = [10: Color.orange, 100: Color.red, 300: Color.blue]

  public GameCanvas(GameBoard board) {
    setUp(board);
  }

  public GameCanvas(GameBoard board, int pixelsSquarePerCell) {
    GameCanvas.pixelsSquarePerCell = pixelsSquarePerCell;
    setUp(board);
  }

  private void setUp(GameBoard board) {
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
    board.getCells().each {cell ->
      new CellImage(cell, this, board)
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