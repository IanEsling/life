package life.rules;

import life.*;

/**
 */
public interface RuleHandler
{
    void applyRule(Cell cell);

    boolean isEligible(Cell cell);
}
