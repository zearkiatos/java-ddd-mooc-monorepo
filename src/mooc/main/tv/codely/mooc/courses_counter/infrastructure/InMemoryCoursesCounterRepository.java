package tv.codely.mooc.courses_counter.infrastructure;

import org.springframework.context.annotation.Profile;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.shared.domain.ServiceInjectable;

import java.util.Optional;

@ServiceInjectable
@Profile({"test"})
public final class InMemoryCoursesCounterRepository implements CoursesCounterRepository {
    private CoursesCounter counter;

    @Override
    public void save(CoursesCounter counter) {
        this.counter = counter;
    }

    @Override
    public Optional<CoursesCounter> search() {
        return Optional.ofNullable(counter);
    }
}
