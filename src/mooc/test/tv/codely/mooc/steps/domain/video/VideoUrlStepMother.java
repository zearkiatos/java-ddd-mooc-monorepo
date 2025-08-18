package tv.codely.mooc.steps.domain.video;

import tv.codely.shared.domain.VideoUrl;
import tv.codely.shared.domain.UrlMother;

public final class VideoUrlStepMother {
    public static VideoUrl create(String value) {
        return new VideoUrl(value);
    }

    public static VideoUrl random() {
        return create(UrlMother.random());
    }

}
