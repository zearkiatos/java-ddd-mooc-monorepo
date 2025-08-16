package tv.codely.mooc.steps.domain.challenge;
import tv.codely.mooc.steps.domain.*;
public final class ChallengeStep extends Step {

    private final ChallengeStepStatement statement;

    public ChallengeStep(StepId id, StepTitle title, ChallengeStepStatement statement) {
        super(id, title);
        this.statement = statement;
    }

    public ChallengeStepStatement statement() {
        return statement;
    }

    public ChallengeStep() {
        super(null, null);
        this.statement = new ChallengeStepStatement();
    }
}
