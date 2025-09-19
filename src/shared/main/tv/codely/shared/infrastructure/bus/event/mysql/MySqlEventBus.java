package tv.codely.shared.infrastructure.bus.event.mysql;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.hibernate.query.NativeQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.amqp.AmqpException;

import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.bus.event.inMemory.InMemoryEventBus;
import tv.codely.shared.domain.Utils;
import tv.codely.shared.domain.ServiceInjectable;


@Profile({"local"})
@ServiceInjectable
public final class MySqlEventBus implements EventBus {
    private static SessionFactory sessionFactory;
    private final InMemoryEventBus failoverPublisher;

    public MySqlEventBus(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.failoverPublisher = new InMemoryEventBus();
    }

    @Override
    public void publish(List<DomainEvent<?>> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent<?> domainEvent) {
        try {
            String id = domainEvent.eventId();
            String aggregateId = domainEvent.aggregateId();
            String name = domainEvent.eventName();
            HashMap<String, Serializable> body = domainEvent.toPrimitives();
            String ocurredOn = domainEvent.occurredOn();

            NativeQuery query = sessionFactory.getCurrentSession().createNativeQuery(
                "INSERT INTO domain_events (id, aggregate_id, name, body, occurred_on)" +
                "VALUES (:id, :aggregateId, :name, :body, :occurredOn);"
            );


            query.setParameter("id", id)
            .setParameter("aggregateId", aggregateId)
            .setParameter("name", name)
            .setParameter("body", Utils.jsonEncode(body))
            .setParameter("occurredOn", ocurredOn);

            query.executeUpdate();
        }
        catch (AmqpException e) {
           failoverPublisher.publish(Collections.singletonList(domainEvent));
        }

    }
}
