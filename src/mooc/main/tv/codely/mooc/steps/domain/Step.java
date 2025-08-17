package tv.codely.mooc.steps.domain;

import java.util.Objects;

public abstract class Step {
    private final StepId id;
    private final StepTitle title;

    protected Step(StepId id, StepTitle title) {
        this.id = id;
        this.title = title;
    }

    public StepId id() {
        return id;
    }

    public StepTitle title() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Step)) return false;
        Step step = (Step) o;
        return id.equals(step.id) && title.equals(step.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
