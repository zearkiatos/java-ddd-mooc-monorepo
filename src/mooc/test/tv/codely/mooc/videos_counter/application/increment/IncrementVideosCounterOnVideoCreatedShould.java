package tv.codely.mooc.videos_counter.application.increment;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tv.codely.mooc.videos.domain.VideoCreatedDomainEvent;
import tv.codely.mooc.videos.domain.VideoCreatedDomainEventMother;
import tv.codely.mooc.videos_counter.VideosCounterModuleUnitTestCase;
import tv.codely.mooc.videos_counter.application.increment.IncrementVideosCounterOnVideoCreated;
import tv.codely.mooc.videos_counter.application.increment.VideosCounterIncrementer;
import tv.codely.mooc.videos_counter.domain.VideosCounter;
import tv.codely.mooc.videos_counter.domain.VideosCounterMother;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoIdMother;

final class IncrementVideosCounterOnVideoCreatedShould extends VideosCounterModuleUnitTestCase {
    IncrementVideosCounterOnVideoCreated subscriber;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        subscriber = new IncrementVideosCounterOnVideoCreated(
            new VideosCounterIncrementer(repository, uuidGenerator)
        );
    }

    @Test
    void it_should_initialize_a_new_counter() {
        VideoCreatedDomainEvent event = VideoCreatedDomainEventMother.random();

        VideoId videoId = VideoIdMother.create(event.aggregateId());
        VideosCounter newCounter = VideosCounterMother.withOne(videoId);

        shouldSearch();
        shouldGenerateUuid(newCounter.id().value());

        subscriber.on(event);

        shouldHaveSaved(newCounter);
    }

    @Test
    void it_should_increment_an_existing_counter() {
        VideoCreatedDomainEvent event = VideoCreatedDomainEventMother.random();

        VideoId videoId = VideoIdMother.create(event.aggregateId());
        VideosCounter existingCounter = VideosCounterMother.random();
        VideosCounter incrementedCounter = VideosCounterMother.incrementing(existingCounter, videoId);

        shouldSearch(existingCounter);

        subscriber.on(event);

        shouldHaveSaved(incrementedCounter);
    }

    @Test
    void it_should_not_increment_an_already_incremented_video() {
        VideoCreatedDomainEvent event = VideoCreatedDomainEventMother.random();

        VideoId videoId = VideoIdMother.create(event.aggregateId());
        VideosCounter existingCounter = VideosCounterMother.withOne(videoId);

        shouldSearch(existingCounter);

        subscriber.on(event);
    }
}
