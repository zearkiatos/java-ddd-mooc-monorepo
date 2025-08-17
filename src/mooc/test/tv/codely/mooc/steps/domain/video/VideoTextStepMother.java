package tv.codely.mooc.steps.domain.video;

import tv.codely.shared.domain.WordMother;

public class VideoTextStepMother {
    public static VideoTextStep create(String value) {
        return new VideoTextStep(value);
    }

    public static VideoTextStep random() {
        return create(WordMother.random());
    }
}
