package life;

/**
 */
public class CellNotFoundException extends RuntimeException
{
    CellNotFoundException(int row, int column)
    {
        super("cell not found, row "+row+", column "+column);
    }
}
