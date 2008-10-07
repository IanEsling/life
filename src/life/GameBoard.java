package life;

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
        for (Cell cell : board)
        {
            for (int row = startIndex(cell.getRow()); row <= endRow(cell.getRow()); row++)
            {
                for (int column = startIndex(cell.getColumn()); column <= endColumn(cell.getColumn()); column++)
                {
                    if (!(cell.getRow() == row && cell.getColumn() == column))
                    {
                        cell.neighbouringCell(getCell(row, column));
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

    Cell getCell(int row, int column)
    {
        if (!board.contains(new Cell(row, column)))
        {
            throw new CellNotFoundException(row, column);
        }
        return board.get(board.indexOf(new Cell(row, column)));
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

    class DieIfLessThanTwoLiveNeighboursRule implements RuleHandler
    {
        public void applyRule(Cell cell)
        {
            cell.newState(false);
        }

        public boolean isEligible(Cell cell)
        {
            return cell.isAlive() && (cell.getNumberOfLiveNeighbours() < 2);
        }
    }

    class DieIfMoreThanThreeLiveNeighboursRule implements RuleHandler
    {
        public void applyRule(Cell cell)
        {
            cell.newState(false);
        }

        public boolean isEligible(Cell cell)
        {
            return cell.isAlive() && (cell.getNumberOfLiveNeighbours() > 3);
        }
    }

    class ComeToLifeIfExactlyThreeLiveNeighboursRule implements RuleHandler
    {

        public void applyRule(Cell cell)
        {
            cell.newState(true);
        }

        public boolean isEligible(Cell cell)
        {
            return !cell.isAlive() && cell.getNumberOfLiveNeighbours() == 3;
        }
    }
}
