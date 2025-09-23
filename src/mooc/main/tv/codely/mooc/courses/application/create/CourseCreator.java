package tv.codely.mooc.courses.application.create;

import org.springframework.transaction.annotation.Transactional;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.videos.application.create.CreateVideoRequest;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.bus.event.EventBus;

@ServiceInjectable
public class CourseCreator {
    private CourseRepository repository;
    private final EventBus eventBus;

    public CourseCreator(CourseRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @Transactional
    public void create(CourseId id, CourseName name, CourseDuration duration) {
        Course course = Course.create(id, name, duration);

        repository.save(course);
        eventBus.publish(course.pullDomainEvents());
    }


}
