package tv.codely.mooc.videos.application.create;

import tv.codely.mooc.videos.domain.*;

public final class CreateVideoRequestMother {
    public static CreateVideoRequest create(VideoId id, VideoTitle title, VideoDescription description, VideoUrl url) {
        return new CreateVideoRequest(
                id.value(),
                title.value(),
                description.value(),
                url.value());
    }

    public static CreateVideoRequest random() {
        return create(
                VideoIdMother.random(),
                VideoTitleMother.random(),
                VideoDescriptionMother.random(),
                VideoUrlMother.random());
    }
}
