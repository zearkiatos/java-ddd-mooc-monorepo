package tv.codely.mooc.shared.infrastructure.bus.event.rabbitmq;

import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;
import tv.codely.shared.domain.ServiceInjectable;


@ServiceInjectable
@DomainEventSubscriber({CourseCreatedDomainEvent.class})
public final class TestAllWorksOnRabbitMqEventsPublished {
    public Boolean hasBeenExecuted = false;

    public void on(CourseCreatedDomainEvent event) {
        hasBeenExecuted = true;
    }
}
