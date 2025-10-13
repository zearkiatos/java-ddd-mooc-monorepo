package tv.codely.mooc.videos.domain;

import tv.codely.mooc.videos.application.create.CreateVideoCommand;
import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoDescription;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoTitle;
import tv.codely.shared.domain.VideoUrl;

public final class VideoMother {
    public static Video create(VideoId id, VideoTitle title, VideoDescription description, VideoUrl url) {
        return new Video(id, title, description, url);
    }

    public static Video fromRequest(CreateVideoCommand request) {
        return create(
                VideoIdMother.create(request.id()),
                VideoTitleMother.create(request.title()),
                VideoDescriptionMother.create(request.description()),
                VideoUrlMother.create(request.url()));
    }

    public static Video random() {
        return create(
                VideoIdMother.random(),
                VideoTitleMother.random(),
                VideoDescriptionMother.random(),
                VideoUrlMother.random());
    }
}
