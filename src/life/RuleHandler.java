package life;

/**
 */
public interface RuleHandler
{
    boolean handleRule();

    boolean isEligible(Cell cell);
}
