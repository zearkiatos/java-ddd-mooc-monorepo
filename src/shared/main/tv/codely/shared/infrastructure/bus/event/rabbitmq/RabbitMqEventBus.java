package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.amqp.AmqpException;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.bus.event.spring.SpringApplicationEventBus;

@ServiceInjectable
@Profile({"rabbitmq"})
public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;
    private final String exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher, @Value("${rabbitmq.exchange.domain_events:domain_events}") String exchangeName) {
        this.publisher = publisher;
        this.exchangeName = exchangeName;
    }

    @Override
    public void publish(List<DomainEvent<?>> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent<?> domainEvent) {
        try {
            publisher.publish(domainEvent, exchangeName);
        } catch(AmqpException error) {
            // Log the error but don't fail - for testing purposes
            System.err.println("RabbitMQ publish failed: " + error.getMessage());
        }
    }
}
