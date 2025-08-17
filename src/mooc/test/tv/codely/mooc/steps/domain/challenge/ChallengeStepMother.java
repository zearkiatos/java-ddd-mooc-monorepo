package tv.codely.mooc.steps.domain.challenge;

import tv.codely.mooc.steps.domain.challenge.ChallengeStepStatement;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.StepTitle;
import tv.codely.mooc.steps.domain.StepTitleMother;
import tv.codely.mooc.steps.domain.StepIdMother;


public final class ChallengeStepMother {
    public static ChallengeStep create(StepId id, StepTitle title, ChallengeStepStatement statement) {
        return new ChallengeStep(id, title, statement);
    }

    public static ChallengeStep random() {
        return create(StepIdMother.random(), StepTitleMother.random(), ChallengeStepStatementMother.random());
    }
}
