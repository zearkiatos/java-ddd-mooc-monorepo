package tv.codely.backoffice.courses.application.create;

import org.springframework.context.event.EventListener;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
@DomainEventSubscriber({CourseCreatedDomainEvent.class})
public final class CreateBackofficeCourseOnCourseCreated {
    private final BackofficeCourseCreator creator;

    public CreateBackofficeCourseOnCourseCreated(BackofficeCourseCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(CourseCreatedDomainEvent event) {
        this.creator.create(
                event.aggregateId(),
                event.name(),
                event.duration()
        );
    }
}
