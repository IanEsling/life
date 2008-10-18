package life.rules;

import life.cells.*;

/**
 */
public class ComeToLifeIfExactlyThreeLiveNeighboursRule implements RuleHandler
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
