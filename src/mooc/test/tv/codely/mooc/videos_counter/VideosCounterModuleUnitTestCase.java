package tv.codely.mooc.videos_counter;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.util.Optional;

import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.videos_counter.domain.VideosCounter;
import tv.codely.mooc.videos_counter.domain.VideosCounterRepository;

import tv.codely.shared.infrastructure.UnitTestCase;

public abstract class VideosCounterModuleUnitTestCase extends UnitTestCase {
    protected VideosCounterRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        repository = mock(VideosCounterRepository.class);
    }

    public void shouldSearch(VideosCounter video) {
        Mockito.when(repository.search()).thenReturn(Optional.of(video));
    }

    public void shouldSearch() {
        Mockito.when(repository.search()).thenReturn(Optional.empty());
    }

    public void shouldHaveSaved(VideosCounter video) {
        verify(repository, atLeastOnce()).save(video);
    }
}
