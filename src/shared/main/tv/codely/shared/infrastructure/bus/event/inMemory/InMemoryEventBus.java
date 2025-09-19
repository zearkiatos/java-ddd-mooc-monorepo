package tv.codely.shared.infrastructure.bus.event.inMemory;

import java.util.List;
import org.springframework.context.annotation.Primary;
import java.io.Serializable;
import java.util.HashMap;

import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.ServiceInjectable;

@SessionInjectable
public final class InMemoryEventBus implements EventBus {
    private List<DomainEvent<?>> domainEventRepository = new ArrayList<>();
    @Override
    public void publish(List<DomainEvent<?>> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent<?> domainEvent) {
        String id = domainEvent.eventId();
        String aggregateId = domainEvent.aggregateId();
        String name = domainEvent.eventName();
        HashMap<String, Serializable> body = domainEvent.toPrimitives();
        String ocurredOn = domainEvent.occurredOn();

        domainEventRepository.add(domainEvent);
    }
}
