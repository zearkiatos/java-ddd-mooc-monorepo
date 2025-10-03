package tv.codely.mooc.shared.infrastructure.bus.event.rabbitmq;

import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;
import tv.codely.shared.domain.ServiceInjectable;
import org.springframework.context.annotation.Profile;


@ServiceInjectable
@Profile({"test", "rabbitmq"})  // Add profile to ensure it's active in rabbitmq profile
@DomainEventSubscriber({CourseCreatedDomainEvent.class})
public final class TestAllWorksOnRabbitMqEventsPublished {
    public Boolean hasBeenExecuted = false;

    public void on(CourseCreatedDomainEvent event) {
        hasBeenExecuted = true;
    }
}
