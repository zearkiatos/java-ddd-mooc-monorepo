package tv.codely.mooc.students;

import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.mooc.infrastructure.UnitTestCase;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentRepository;

public abstract class StudentsModuleUnitTestCase extends UnitTestCase {
    protected StudentRepository repository;
    protected EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        repository = mock(StudentRepository.class);
        eventBus = mock(EventBus.class);
    }

    protected void shouldHaveSaved(Student student) {
        verify(repository, atLeastOnce()).save(student);
    }

    public void shouldHavePublished(List<DomainEvent<?>> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(domainEvents);
    }

    public void shouldHavePublished(DomainEvent<?> domainEvent) {
        shouldHavePublished(Collections.singletonList(domainEvent));
    }
}
