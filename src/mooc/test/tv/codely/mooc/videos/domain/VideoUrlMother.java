package tv.codely.mooc.videos.domain;

import tv.codely.shared.domain.UrlMother;
import tv.codely.shared.domain.VideoUrl;

public final class VideoUrlMother {
    public static VideoUrl create(String value) {
        return new VideoUrl(value);
    }

    public static VideoUrl random() {
        return create(UrlMother.random());
    }
}
