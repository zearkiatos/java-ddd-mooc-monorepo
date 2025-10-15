package tv.codely.mooc.videos_counter.application.find;

import tv.codely.mooc.videos_counter.application.find.VideosCounterResponse;
import tv.codely.shared.domain.IntegerMother;

public class VideosCounterResponseMother {
    public static VideosCounterResponse create(Integer total) {
        return new VideosCounterResponse(total);
    }

    public static VideosCounterResponse random() {
                return create(IntegerMother.random());
    }
}
