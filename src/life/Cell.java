package life;

/**
 */
public class Cell
{
    final int row, column;
    boolean alive;

    Cell(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.alive = false;
    }

    int getRow()
    {
        return row;
    }

    int getColumn()
    {
        return column;
    }

    boolean isAlive()
    {
        return alive;
    }

    void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    Cell copy()
    {
        Cell cell = new Cell(row, column);
        cell.setAlive(isAlive());
        return cell;
    }
    
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return column == cell.column && row == cell.row;
    }

    public int hashCode()
    {
        int result;
        result = row;
        result = 31 * result + column;
        return result;
    }

    public String toString()
    {
        return "Cell row: "+row+", column: "+column+", isAlive: "+isAlive();
    }
}
