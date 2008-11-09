package life.board

import java.awt.Color
import java.awt.image.BufferedImage


/**
 */

public class CellImage implements CellListener {
  BufferedImage canvasImage;
  Cell cell;
  List pixels = new ArrayList();
  int ticksInState;
  def pixelArea = GameCanvas.pixelsSquarePerCell

  CellImage(Cell cell, GameCanvas gameCanvas, GameBoard board) {
    this.cell = cell;
    this.canvasImage = gameCanvas.getImage();
    cell.addCellListener(this);
    setPixels();
    board.addTickListener({boardTicker()})
  }

  void boardTicker() {
    ticksInState++;
    if (cell.isAlive() && GameCanvas.colourTransitions.keySet().contains(ticksInState))
      paintPixels(GameCanvas.colourTransitions.get(ticksInState));
  }

  public void listenedToCellHasComeToLife() {
    paintPixels(Color.green);
    ticksInState = 0;
  }

  public void listenedToCellHasDied() {
    paintPixels(Color.black);
    ticksInState = 0;
  }

  private void setPixels() {
    (0..pixelArea - 1).each {row ->
      (0..pixelArea - 1).each {column ->
        pixels.add(new Pixel(cell, pixelArea, row, column))
      }
    }
  }

  private void paintPixels(Color colour) {
    pixels.each {pixel ->
      canvasImage.setRGB(pixel.x, pixel.y, colour.getRGB());
    }
  }
}

class Pixel {
  int x, y;

  Pixel(Cell cell, int pixelArea, int row, int col) {
    this.x = ((cell.row - 1) * pixelArea) + row;
    this.y = ((cell.column - 1) * pixelArea) + col;
  }
}
