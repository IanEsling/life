package life;

import java.awt.image.*;
import java.awt.*;

/**
 */
class CellImage extends BufferedImage implements CellListener
{
    public static final Integer cellImageWidth = 3;
    public static final Integer cellImageHeight = 3;
    Cell cell;

    public CellImage(Cell cell)
    {
        super(cellImageWidth, cellImageHeight, BufferedImage.TYPE_INT_RGB);
        this.cell = cell;
        cell.cellListener(this);
    }

    public void draw()
    {
        for (int row = 0; row < cellImageHeight; row++)
        {
            for (int col = 0; col < cellImageWidth; col++)
            {
//                this.getRaster().ssetPixel(row, col, getColour().);
            }
        }
        setData(getRaster());
    }

    public Color getColour()
    {
        return cell.isAlive() ? Color.white : Color.black;
    }

    public Cell getCell()
    {
        return cell;
    }

    public void neighbourComeToLife()
    {
        draw();
    }

    public void neighbourHasDied()
    {
        draw();
    }
}
