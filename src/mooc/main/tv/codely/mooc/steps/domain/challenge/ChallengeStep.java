package tv.codely.mooc.steps.domain.challenge;
import tv.codely.mooc.steps.domain.*;

public final class ChallengeStep extends Step {

    private ChallengeStepStatement statement;

    public ChallengeStep(StepId id, StepTitle title, ChallengeStepStatement statement) {
        super(id, title);
        this.statement = statement;
    }

    // Default constructor for Hibernate
    public ChallengeStep() {
        super();
        this.statement = new ChallengeStepStatement();
    }

    public ChallengeStepStatement statement() {
        return statement;
    }

    // Hibernate getters/setters
    public ChallengeStepStatement getStatement() {
        return statement;
    }

    public void setStatement(ChallengeStepStatement statement) {
        this.statement = statement;
    }
}
