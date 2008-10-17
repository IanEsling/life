package life;

import java.awt.*;
import java.awt.image.*;

/**
 */
class CellImage implements CellListener
{
    BufferedImage canvas;
    int pixelsPerSide, cellRow, cellColumn;

    CellImage(Cell cell, BufferedImage canvas, int pixelsPerSide)
    {
        cellRow = cell.getRow();
        cellColumn = cell.getColumn();
        this.canvas = canvas;
        this.pixelsPerSide = pixelsPerSide;
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
        for (int row = 1;row<=pixelsPerSide;row++)
        {
            for (int col = 1;col<=pixelsPerSide;col++)
            {
                canvas.setRGB((cellRow - 1) * row, (cellColumn - 1) * col, colour.getRGB());
            }
        }
    }
}
