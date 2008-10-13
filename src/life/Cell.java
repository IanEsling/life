package life;

import java.util.*;

/**
 */
public class Cell implements CellListener
{
    List<CellListener> listeningCells = new ArrayList<CellListener>();
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
        for (CellListener cell : listeningCells)
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

    public void neighbourHasDied()
    {
        --numberOfLiveNeighbours;
    }

    public void neighbourComeToLife()
    {
        ++numberOfLiveNeighbours;
    }

    public int getNumberOfLiveNeighbours()
    {
        return numberOfLiveNeighbours;
    }

    void cellListener(CellListener cellListener)
    {
        listeningCells.add(cellListener);
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
                listeningCells.size() + " listeningCells";
    }
}
