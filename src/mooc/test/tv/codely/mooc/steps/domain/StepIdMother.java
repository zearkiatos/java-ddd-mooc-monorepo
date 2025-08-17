package tv.codely.mooc.steps.domain;

import tv.codely.shared.domain.UUIDMother;

public final class StepIdMother {
    public static StepId create(String value) {
        return new StepId(value);
    }

    public static StepId random() {
        return create(UUIDMother.random());
    }
}
