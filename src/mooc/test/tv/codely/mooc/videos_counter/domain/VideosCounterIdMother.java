package tv.codely.mooc.videos_counter.domain;

import tv.codely.mooc.videos_counter.domain.VideosCounterId;
import tv.codely.shared.domain.UUIDMother;

public final class VideosCounterIdMother {
    public static VideosCounterId create(String value) {
        return new VideosCounterId(value);
    }

    public static VideosCounterId random() {
        return create(UUIDMother.random());
    }
}
