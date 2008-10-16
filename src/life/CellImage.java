package life;

import java.awt.image.*;
import java.awt.*;

/**
 */
class CellImage extends BufferedImage
{
    public static final Integer cellImageWidth = 3;
    public static final Integer cellImageHeight = 3;
    Cell cell;

    public CellImage(Cell cell)
    {
        super(cellImageWidth, cellImageHeight, BufferedImage.TYPE_INT_RGB);
        this.cell = cell;
    }

    public Color getColour()
    {
        return cell.isAlive() ? Color.white : Color.black;
    }

    public Cell getCell()
    {
        return cell;
    }
}
