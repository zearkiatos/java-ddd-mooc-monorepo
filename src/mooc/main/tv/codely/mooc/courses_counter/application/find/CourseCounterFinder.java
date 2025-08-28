package tv.codely.mooc.courses_counter.application.find;

import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
public final class CourseCounterFinder {
    private CoursesCounterRepository repository;

    public CourseCounterFinder(CoursesCounterRepository repository) {
        this.repository = repository;
    }

    public CoursesCounterResponse find() {
        CoursesCounter coursesCounter = repository.search().orElseGet(() -> {
            throw new CoursesCounterNotInitializaed();
        });

        return new CoursesCounterResponse(coursesCounter.total().value());
    }
}
