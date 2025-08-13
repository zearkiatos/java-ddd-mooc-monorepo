package tv.codely.mooc.videos.application.create;

import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoDescription;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoRepository;
import tv.codely.mooc.videos.domain.VideoTitle;
import tv.codely.mooc.videos.domain.VideoUrl;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
public final class VideoCreator {
    private VideoRepository repository;

    public VideoCreator(VideoRepository repository) {
        this.repository = repository;
    }

    public void create(CreateVideoRequest request) {
        Video video = new Video(
                new VideoId(request.id()),
                new VideoTitle(request.title()),
                new VideoDescription(request.description()),
                new VideoUrl(request.url()));

        repository.save(video);
    }
}
