package tv.codely.mooc.courses_counter.application.find;

import org.springframework.context.annotation.Profile;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.mooc.courses_counter.domain.CoursesCounterNotInitialized;

@ServiceInjectable
public final class CoursesCounterFinder {
    private CoursesCounterRepository repository;

    public CoursesCounterFinder(CoursesCounterRepository repository) {
        this.repository = repository;
    }

    public CoursesCounterResponse find() {
        CoursesCounter coursesCounter = repository.search().orElseGet(() -> {
            throw new CoursesCounterNotInitialized();
        });

        return new CoursesCounterResponse(coursesCounter.total().value());
    }
}
