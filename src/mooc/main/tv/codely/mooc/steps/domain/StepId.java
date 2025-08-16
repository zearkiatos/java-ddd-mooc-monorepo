package tv.codely.mooc.steps.domain;

import tv.codely.shared.domain.Identifier;

public final class StepId extends Identifier {
    public StepId(String value) {
        super(value);
    }

    public StepId() {
        super();
    }

    public String getValue() {
        return value();
    }

}
