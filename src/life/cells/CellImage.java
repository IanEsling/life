package life.cells;

import java.awt.*;
import java.awt.image.*;

/**
 */
class CellImage implements CellListener
{
    BufferedImage canvas;
    int pixelsPerSide, cellRow, cellColumn;

    CellImage(Cell cell, GameCanvas gamecanvas)
    {
        cellRow = cell.getRow();
        cellColumn = cell.getColumn();
        this.canvas = gamecanvas.getImage();
        this.pixelsPerSide = gamecanvas.pixelsSquarePerCell;
        cell.cellListener(this);
    }

    public void neighbourComeToLife()
    {
        paintPixels(Color.white);
    }

    public void neighbourHasDied()
    {
        paintPixels(Color.black);
    }

    private void paintPixels(Color colour)
    {
        for (int row = 0; row < pixelsPerSide; row++)
        {
            for (int col = 0; col < pixelsPerSide; col++)
            {
                canvas.setRGB(((cellRow - 1)*pixelsPerSide) + row, ((cellColumn - 1)*pixelsPerSide) + col, colour.getRGB());
            }
        }
    }
}
