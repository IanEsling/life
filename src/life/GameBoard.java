package life;

import life.rules.*;

import java.util.*;

/**
 */
public class GameBoard
{
    List<Cell> board;
    int totalRows, totalColumns;
    List<RuleHandler> rules = new ArrayList<RuleHandler>();

    GameBoard(int rows, int columns)
    {
        newBoard(rows, columns);
        setRules();
    }

    void setRules()
    {
        setDieIfLessThanTwoLiveNeighboursRule();
        setDieIfMoreThanThreeLiveNeighboursRule();
        setComeToLifeIfExactlyThreeLiveNeighboursRule();
    }

    List<Cell> getBoard()
    {
        return board;
    }

    private void newBoard(int rows, int columns)
    {
        this.totalRows = rows;
        this.totalColumns = columns;
        createBoard(rows, columns);
        makeCellsNeighbourAware();
    }

    private void makeCellsNeighbourAware()
    {
        Cell[][] cells = new Cell[totalRows][totalColumns];
        for (Cell cell : board)
        {
            cells[cell.getRow()-1][cell.getColumn()-1] = cell;
        }

        for (Cell cell : board)
        {
            for (int row = startIndex(cell.getRow()); row <= endRow(cell.getRow()); row++)
            {
                for (int column = startIndex(cell.getColumn()); column <= endColumn(cell.getColumn()); column++)
                {
                    if (!(cell.getRow() == row && cell.getColumn() == column))
                    {
                        cell.cellListener(cells[row-1][column-1]);
                    }
                }
            }
        }
    }

    private void createBoard(int rows, int columns)
    {
        board = new ArrayList<Cell>();
        for (int row = 1; row <= rows; row++)
        {
            for (int column = 1; column <= columns; column++)
            {
                board.add(new Cell(row, column));
            }
        }
    }

    void tick()
    {
        for (Cell cell : board)
        {
            applyRules(cell);
        }
        for (Cell cell : board)
        {
            cell.applyNewState();
        }
    }

    private int liveCells()
    {
        int total = 0;
        for (Cell cell : board)
        {
            if (cell.isAlive()) total++;
        }
        return total;
    }

    private void applyRules(Cell cell)
    {
        for (RuleHandler rule : rules)
        {
            if (rule.isEligible(cell))
            {
                rule.applyRule(cell);
            }
        }
    }

    Cell getCell(Cell cell)
    {
        if (!board.contains(cell))
        {
            throw new CellNotFoundException(cell);
        }
        return board.get(board.indexOf(cell));
    }

    private int endColumn(int cellColumn)
    {
        return (totalColumns == cellColumn ? totalColumns : cellColumn + 1);
    }

    private int endRow(int cellRow) {return (totalRows == cellRow ? totalRows : cellRow + 1);}

    private int startIndex(int idx) {return (idx == 1 ? 1 : idx - 1);}

    void setDieIfLessThanTwoLiveNeighboursRule()
    {
        rules.add(new DieIfLessThanTwoLiveNeighboursRule());
    }

    void setDieIfMoreThanThreeLiveNeighboursRule()
    {
        rules.add(new DieIfMoreThanThreeLiveNeighboursRule());
    }

    void setComeToLifeIfExactlyThreeLiveNeighboursRule()
    {
        rules.add(new ComeToLifeIfExactlyThreeLiveNeighboursRule());
    }

}
