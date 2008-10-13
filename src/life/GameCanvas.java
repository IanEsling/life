package life;

import java.awt.*;

/**
 */
public class GameCanvas extends Canvas
{
    GameBoard board;
    CellImage[][] cells;

    GameCanvas(GameBoard board)
    {
        this.board = board;
        cells = new CellImage[board.totalRows][board.totalColumns];
        for (Cell cell : board.getBoard())
        {
            cells[cell.getRow()-1][cell.getColumn()-1] = new CellImage(cell);
        }
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        for (CellImage[] row : cells)
        {
            for (CellImage cell : row)
            {
                Graphics2D cellGraphics = cell.createGraphics();
                cellGraphics.setPaint(cell.getColour());
                cellGraphics.fillRect(0,0,2,2);
                g2d.drawImage(cell, cell.getCell().getRow()*3, cell.getCell().getColumn()*3, this);
            }
        }
        setSize(cells.length*4, cells[0].length*4);
        setBackground(Color.BLACK);
    }
}
