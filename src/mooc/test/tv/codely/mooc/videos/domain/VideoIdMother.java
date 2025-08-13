package tv.codely.mooc.videos.domain;

import tv.codely.shared.domain.UUIDMother;

public final class VideoIdMother {
    public static VideoId create(String value) {
        return new VideoId(value);
    }

    public static VideoId random() {
        return create(UUIDMother.random().toString());
    }
}
