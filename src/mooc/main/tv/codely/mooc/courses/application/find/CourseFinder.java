package tv.codely.mooc.courses.application.find;

import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.domain.CourseNotExist;

@ServiceInjectable
public final class CourseFinder {
    private final CourseRepository repository;

    public CourseFinder(CourseRepository repository) {
        this.repository = repository;
    }

    CourseResponse find(CourseId id) throws CourseNotExist {
        return repository.search(id)
            .map(CourseResponse::fromAggregate)
            .orElseThrow(() -> new CourseNotExist(id));
    }
}
