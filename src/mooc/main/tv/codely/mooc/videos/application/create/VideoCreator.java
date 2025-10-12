package tv.codely.mooc.videos.application.create;

import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoDescription;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoRepository;
import tv.codely.mooc.videos.domain.VideoTitle;
import tv.codely.shared.domain.VideoUrl;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
public class VideoCreator {
    private VideoRepository repository;
    private final EventBus eventBus;

    public VideoCreator(VideoRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(VideoId id, VideoTitle title, VideoDescription description, VideoUrl url) {
        Video video = Video.create(id, title, description, url);

        repository.save(video);
        eventBus.publish(video.pullDomainEvents());
    }
}
