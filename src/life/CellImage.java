package life;

import java.awt.image.*;
import java.awt.*;

/**
 */
class CellImage implements CellListener
{
    BufferedImage canvas;
    Cell cell;

    CellImage(Cell cell)
    {
        this.cell = cell;
        cell.cellListener(this);
    }

    void setImage(BufferedImage canvas)
    {
        this.canvas = canvas;
    }

    public void neighbourComeToLife()
    {
        canvas.setRGB(cell.getRow()-1, cell.getColumn()-1, Color.white.getRGB());
    }

    public void neighbourHasDied()
    {
        canvas.setRGB(cell.getRow()-1, cell.getColumn()-1, Color.black.getRGB());
    }
}
