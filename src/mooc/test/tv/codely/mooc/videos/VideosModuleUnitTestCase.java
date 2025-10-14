package tv.codely.mooc.videos;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoRepository;
import tv.codely.shared.infrastructure.UnitTestCase;


public abstract class VideosModuleUnitTestCase extends UnitTestCase {
    protected VideoRepository repository;
    protected EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        repository = mock(VideoRepository.class);
        eventBus = mock(EventBus.class);
    }

    protected void shouldHaveSaved(Video video) {
        verify(repository, atLeastOnce()).save(video);
    }

        public void shouldHavePublished(List<DomainEvent<?>> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(domainEvents);
    }

    public void shouldHavePublished(DomainEvent<?> domainEvent) {
        shouldHavePublished(Collections.singletonList(domainEvent));
    }
}
