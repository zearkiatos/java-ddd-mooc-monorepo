package tv.codely.mooc.videos.domain;

import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoCreatedDomainEvent;
import tv.codely.mooc.videos.domain.VideoDescription;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoTitle;
import tv.codely.shared.domain.VideoUrl;
import tv.codely.mooc.videos.domain.VideoUrlMother;
import tv.codely.mooc.videos.domain.VideoDescriptionMother;
import tv.codely.mooc.videos.domain.VideoIdMother;
import tv.codely.mooc.videos.domain.VideoTitleMother;


public final class VideoCreatedDomainEventMother {
    public static VideoCreatedDomainEvent create(VideoId id, VideoTitle title, VideoDescription description, VideoUrl url) {
        return new VideoCreatedDomainEvent(
            id.value(),
            title.value(),
            description.value(),
            url.value()
        );
    }

    public static VideoCreatedDomainEvent fromVideo(Video video) {
        return create(video.id(), video.title(), video.description(), video.url());
    }

    public static VideoCreatedDomainEvent random() {
        return create(
            VideoIdMother.random(),
            VideoTitleMother.random(),
            VideoDescriptionMother.random(),
            VideoUrlMother.random()
        );
    }
}
