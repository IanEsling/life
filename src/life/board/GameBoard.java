package life.board;

import life.rules.*;

import java.util.*;

/**
 */
public class GameBoard
{
    List<Cell> board;
    public int totalRows, totalColumns;
    public List<RuleHandler> rules = new ArrayList<RuleHandler>();
    List<TickListener> tickListeners = new ArrayList<TickListener>();
    public final static int defaultRows = 300, defaultColumns = 300;

    public GameBoard()
    {
        this(defaultRows, defaultColumns);
    }

    public GameBoard(int rows, int columns)
    {
        newBoard(rows, columns);
        setRules();
    }

    public void addTickListener(TickListener listener)
    {
        tickListeners.add(listener);
    }
    
    protected void setRules()
    {
        setDieIfLessThanTwoLiveNeighboursRule();
        setDieIfMoreThanThreeLiveNeighboursRule();
        setComeToLifeIfExactlyThreeLiveNeighboursRule();
        setStayTheSameIfTwoOrThreeNeighboursRule();
    }

    public List<Cell> getCells()
    {
        return board;
    }

    protected void newBoard(int rows, int columns)
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
            cells[cell.getRow() - 1][cell.getColumn() - 1] = cell;
        }

        for (Cell cell : board)
        {
            for (int row = startIndex(cell.getRow()); row <= endRow(cell.getRow()); row++)
            {
                for (int column = startIndex(cell.getColumn()); column <= endColumn(cell.getColumn()); column++)
                {
                    if (!(cell.getRow() == row && cell.getColumn() == column))
                    {
                        addCellListener(cells, cell, row, column);
                    }
                }
            }
        }
    }

    protected void addCellListener(Cell[][] cells, Cell cell, int row, int column)
    {
        cell.addCellListener(cells[row - 1][column - 1]);
    }

    private void createBoard(int rows, int columns)
    {
        board = new ArrayList<Cell>();
        for (int row = 1; row <= rows; row++)
        {
            for (int column = 1; column <= columns; column++)
            {
                addCellToBoard(row, column);
            }
        }
    }

    protected void addCellToBoard(int row, int column) {board.add(new Cell(row, column));}

    public void tick()
    {
        for (Cell cell : board)
        {
            applyRules(cell);
        }
        for (Cell cell : board)
        {
            cell.applyNewState();
        }
        for (TickListener listener : tickListeners)
        {
            listener.boardHasTicked();
        }
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

    public Cell getCell(Cell cell)
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

    public void setDieIfLessThanTwoLiveNeighboursRule()
    {
        rules.add(new DieIfLessThanTwoLiveNeighboursRule());
    }

    public void setDieIfMoreThanThreeLiveNeighboursRule()
    {
        rules.add(new DieIfMoreThanThreeLiveNeighboursRule());
    }

    public void setComeToLifeIfExactlyThreeLiveNeighboursRule()
    {
        rules.add(new ComeToLifeIfExactlyThreeLiveNeighboursRule());
    }

    public void setStayTheSameIfTwoOrThreeNeighboursRule()
    {
        rules.add(new StayTheSameIfTwoOrThreeNeighboursRule());
    }

}
