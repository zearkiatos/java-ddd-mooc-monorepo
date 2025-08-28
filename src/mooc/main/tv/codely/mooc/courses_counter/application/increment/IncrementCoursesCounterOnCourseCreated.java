package tv.codely.mooc.courses_counter.application.increment;

import org.springframework.context.event.EventListener;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
public final class IncrementCoursesCounterOnCourseCreated {
    private final CoursesCounterIncrementer incrementer;

    public IncrementCoursesCounterOnCourseCreated(CoursesCounnterIncrementer incrementer) {
        this.incremnter = incrementer;
    }

    @EventListener
    public void on(CourseCreatedDomainEvent event) {
        CourseId courseId = new CourseId(event.aggregateId());
        incrementer.increment(courseId);
    }
}
