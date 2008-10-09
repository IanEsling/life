package life;

/**
 */
public class CellNotFoundException extends RuntimeException
{
    CellNotFoundException(Cell cell)
    {
        super("cell not found, row "+cell.getRow()+", column "+cell.getColumn());
    }
}
