package tv.codely.mooc.videos.domain;

import tv.codely.shared.domain.LongTextMother;

public final class VideoDescriptionMother {
    public static VideoDescription create(String value) {
        return new VideoDescription(value);
    }

    public static VideoDescription random() {
        return create(LongTextMother.random());
    }
}
