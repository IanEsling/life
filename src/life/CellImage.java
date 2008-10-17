package life;

import java.awt.*;
import java.awt.image.*;

/**
 */
class CellImage implements CellListener
{
    BufferedImage canvas;
    Cell cell;
    int pixelsPerSide;

    CellImage(Cell cell, BufferedImage canvas, int pixelsPerSide)
    {
        this.cell = cell;
        this.canvas = canvas;
        this.pixelsPerSide = pixelsPerSide;
        cell.cellListener(this);
    }

    public void neighbourComeToLife()
    {
        for (int row = 1;row<=pixelsPerSide;row++)
        {
            for (int col = 1;col<=pixelsPerSide;col++)
            {
                canvas.setRGB((cell.getRow() - 1) * row, (cell.getColumn() - 1) * col, Color.white.getRGB());        
            }
        }
    }

    public void neighbourHasDied()
    {
        for (int row = 1;row<=pixelsPerSide;row++)
        {
            for (int col = 1;col<=pixelsPerSide;col++)
            {
                canvas.setRGB((cell.getRow() - 1) * row, (cell.getColumn() - 1) * col, Color.black.getRGB());        
            }
        }
    }
}
