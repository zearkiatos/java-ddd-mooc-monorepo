package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Primary;

import org.springframework.amqp.AmqpException;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlEventBus;


// @Primary
// @Profile({"local"})
// @ServiceInjectable
public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;
    private final MySqlEventBus failoverPublisher;
    private final String exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher, MySqlEventBus faioverPublisher) {
        this.publisher = publisher;
        this.failoverPublisher = faioverPublisher;
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
            failoverPublisher.publish(Collections.singletonList(domainEvent));
        }
    }
}
