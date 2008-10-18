package life.rules;

import life.cells.*;

/**
 */
public interface RuleHandler
{
    void applyRule(Cell cell);

    boolean isEligible(Cell cell);
}
