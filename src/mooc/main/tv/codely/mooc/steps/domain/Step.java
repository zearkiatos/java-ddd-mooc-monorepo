package tv.codely.mooc.steps.domain;

import java.util.Objects;

public abstract class Step {
    private StepId id;
    private StepTitle title;

    protected Step(StepId id, StepTitle title) {
        this.id = id;
        this.title = title;
    }

    // Default constructor for Hibernate
    protected Step() {
    }

    public StepId id() {
        return id;
    }

    public StepTitle title() {
        return title;
    }

    // Hibernate getters/setters
    public StepId getId() {
        return id;
    }

    public void setId(StepId id) {
        this.id = id;
    }

    public StepTitle getTitle() {
        return title;
    }

    public void setTitle(StepTitle title) {
        this.title = title;
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
