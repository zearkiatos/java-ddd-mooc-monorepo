package tv.codely.mooc.steps.domain.video;

import tv.codely.shared.domain.StringValueObject;

public final class VideoTextStep extends StringValueObject {
    public VideoTextStep(String value) {
        super(value);
    }

    // Default constructor for Hibernate
    protected VideoTextStep() {
        super(null);
    }
}
