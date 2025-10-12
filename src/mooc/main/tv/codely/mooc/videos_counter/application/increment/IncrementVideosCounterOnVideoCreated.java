package tv.codely.mooc.videos_counter.application.increment;

import org.springframework.context.event.EventListener;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;
import tv.codely.mooc.videos.domain.VideoCreatedDomainEvent;
import tv.codely.mooc.videos_counter.application.increment.VideosCounterIncrementer;
import tv.codely.mooc.videos.domain.VideoId;


@ServiceInjectable
@DomainEventSubscriber({VideoCreatedDomainEvent.class})
public final class IncrementVideosCounterOnVideoCreated {
    private final VideosCounterIncrementer incrementer;

    public IncrementVideosCounterOnVideoCreated(VideosCounterIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @EventListener
    public void on(VideoCreatedDomainEvent event) {
        VideoId videoId = new VideoId(event.aggregateId());
        incrementer.increment(videoId);
    }
}
