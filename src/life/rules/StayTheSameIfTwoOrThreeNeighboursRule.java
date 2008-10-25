package life.rules;

import life.board.*;

/**
 */
public class StayTheSameIfTwoOrThreeNeighboursRule implements RuleHandler
{
    public void applyRule(Cell cell)
    {
        cell.newState(cell.isAlive());
    }

    public boolean isEligible(Cell cell)
    {
        return cell.isAlive() && (cell.getNumberOfLiveNeighbours() == 2 || cell.getNumberOfLiveNeighbours() ==3);
    }
}