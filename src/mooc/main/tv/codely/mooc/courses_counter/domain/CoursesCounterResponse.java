package tv.codely.mooc.courses_counter.domain;

import java.util.Optional;

public interface CoursesCounterResponse {
    void save(CoursesCounter counter);

    Optional<CoursesCounter> search();
}
