package life.rules;

import life.board.*;

/**
 */
public class DieIfLessThanTwoLiveNeighboursRule implements RuleHandler
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
