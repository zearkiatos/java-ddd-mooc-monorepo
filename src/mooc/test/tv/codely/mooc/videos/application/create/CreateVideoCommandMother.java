package tv.codely.mooc.videos.application.create;

import tv.codely.mooc.videos.domain.*;
import tv.codely.shared.domain.VideoUrl;
import tv.codely.mooc.videos.domain.VideoDescription;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoTitle;
import tv.codely.mooc.videos.domain.VideoUrlMother;
import tv.codely.mooc.videos.domain.VideoDescriptionMother;
import tv.codely.mooc.videos.domain.VideoIdMother;
import tv.codely.mooc.videos.domain.VideoTitleMother;



public final class CreateVideoCommandMother {
    public static CreateVideoCommand create(VideoId id, VideoTitle title, VideoDescription description, VideoUrl url) {
        return new CreateVideoCommand(id.value(), title.value(), description.value(), url.value());
    }

    public static CreateVideoCommand random() {
        return create(
            VideoIdMother.random(),
            VideoTitleMother.random(),
            VideoDescriptionMother.random(),
            VideoUrlMother.random()
        );
    }
}
