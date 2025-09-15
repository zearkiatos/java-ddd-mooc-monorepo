package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import java.util.List;

import org.springframework.amqp.AmqpException;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;


// @ServiceInjectable
public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;
    private final String exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher) {
        this.publisher = publisher;
        this.exchangeName = "domain_events";
    }

    @Override
    public void publish(List<DomainEvent<?>> events) {
        events.forEach(this::publish);
    }

    public void publish(DomainEvent<?> domainEvent) {
        try {
            publisher.publish(domainEvent, exchangeName);
        } catch(AmqpException error) {
            // KAPACHAO
        }
    }
}
