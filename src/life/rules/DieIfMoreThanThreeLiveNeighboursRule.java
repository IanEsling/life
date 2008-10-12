package life.rules;

import life.*;

/**
 */
public class DieIfMoreThanThreeLiveNeighboursRule implements RuleHandler
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
