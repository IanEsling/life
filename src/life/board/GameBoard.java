package life.board;

import life.rules.*;

import java.util.*;

/**
 */
public class GameBoard
{
    CellFactory cellFactory;
    List<Cell> board = new ArrayList<Cell>();
    int totalRows, totalColumns;
    public List<RuleHandler> rules = new ArrayList<RuleHandler>();
    List<TickListener> tickListeners = new ArrayList<TickListener>();
    public final static int defaultRows = 300, defaultColumns = 300;

    public GameBoard()
    {
        this(defaultRows, defaultColumns);
    }

    public GameBoard(int rows, int columns)
    {
        totalColumns = columns;
        totalRows = rows;
        cellFactory = new GroovyCellFactory(this);
        cellFactory.createGameBoardCells();
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

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }
}
