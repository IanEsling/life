package life.board;

/**
 */
public class CellNotFoundException extends RuntimeException
{
    public CellNotFoundException(Cell cell)
    {
        super("cell not found, row "+cell.getRow()+", column "+cell.getColumn());
    }
}
