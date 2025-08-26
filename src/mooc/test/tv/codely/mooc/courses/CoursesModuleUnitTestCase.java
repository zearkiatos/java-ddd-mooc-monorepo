package tv.codely.mooc.courses;

import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.UnitTestCase;

public abstract class CoursesModuleUnitTestCase extends UnitTestCase {
    protected CourseRepository repository;
    protected EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        repository = mock(CourseRepository.class);
        eventBus = mock(EventBus.class);
    }

    protected void shouldHaveSaved(Course course) {
        verify(repository, atLeastOnce()).save(course);
    }

    public void shouldHavePublished(List<DomainEvent<?>> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(domainEvents);
    }

    public void shouldHavePublished(DomainEvent<?> domainEvent) {
        shouldHavePublished(Collections.singletonList(domainEvent));
    }
}

