package life;

import java.util.*;

/**
 */
public class Cell
{
    List<Cell> neighbours = new ArrayList<Cell>();
    final int row, column;
    boolean alive;
    Boolean newState;
    int numberOfLiveNeighbours;

    public Cell(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.alive = false;
        this.numberOfLiveNeighbours = 0;
    }

    int getRow()
    {
        return row;
    }

    int getColumn()
    {
        return column;
    }

    public boolean isAlive()
    {
        return alive;
    }

    void setAlive(boolean alive)
    {
        this.alive = alive;
        this.newState = null;
        tellNeighbours(alive);
    }

    private void tellNeighbours(boolean alive)
    {
        for (Cell cell : neighbours)
        {
            if (alive)
            {
                cell.neighbourComeToLife();
            }
            else
            {
                cell.neighbourHasDied();
            }
        }
    }

    void neighbourHasDied()
    {
        --numberOfLiveNeighbours;
    }

    void neighbourComeToLife()
    {
        ++numberOfLiveNeighbours;
    }

    public int getNumberOfLiveNeighbours()
    {
        return numberOfLiveNeighbours;
    }

    void neighbouringCell(Cell cell)
    {
        neighbours.add(cell);
    }

    public void newState(boolean newState)
    {
        this.newState = newState;
    }

    void applyNewState()
    {
        if (newState!=null) setAlive(newState);
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
        return "Cell row: " + row + ", column: " + column + ", isAlive: " + isAlive() + ", listening to " +
                neighbours.size() + " neighbours";
    }
}
