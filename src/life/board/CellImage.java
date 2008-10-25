package life.board;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.util.List;

/**
 */
class CellImage implements CellListener
{
    BufferedImage canvasImage;
    Cell cell;
    List<Pixel> pixels = new ArrayList<Pixel>();

    class Pixel
    {
        int x, y;

        Pixel(int row, int col)
        {
            this.x = (cell.getRow() - 1) * GameCanvas.pixelsSquarePerCell + row;
            this.y = (cell.getColumn() - 1) * GameCanvas.pixelsSquarePerCell + col;
        }
    }

    CellImage(Cell cell, GameCanvas gameCanvas)
    {
        this.cell = cell;
        this.canvasImage = gameCanvas.getImage();
        cell.addCellListener(this);
        setPixels();
    }

    public void neighbourComeToLife()
    {
        paintPixels(Color.white);
    }

    public void neighbourHasDied()
    {
        paintPixels(Color.black);
    }

    private void setPixels()
    {
        for (int row = 0; row < GameCanvas.pixelsSquarePerCell; row++)
        {
            for (int col = 0; col < GameCanvas.pixelsSquarePerCell; col++)
            {
                pixels.add(new Pixel(row,col));
            }
        }
    }

    private void paintPixels(Color colour)
    {
        for (Pixel pixel : pixels)
        {
            canvasImage.setRGB(pixel.x, pixel.y, colour.getRGB());
        }
    }
}
