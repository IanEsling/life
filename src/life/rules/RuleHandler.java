package life.rules;

import life.board.*;

/**
 */
public interface RuleHandler
{
    void applyRule(Cell cell);

    boolean isEligible(Cell cell);
}
