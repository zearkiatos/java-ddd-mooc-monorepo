package tv.codely.shared.infrastructure.bus.event.mysql;

import org.hibernate.query.NativeQuery;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.Utils;
import tv.codely.shared.infrastructure.bus.event.DomainEventsInformation;
import tv.codely.shared.infrastructure.bus.event.spring.SpringApplicationEventBus;
import tv.codely.shared.infrastructure.ServiceInjectable;
import tv.codely.shared.infrastructure.bus.event.DomainEventsInformation;

@ServiceInjectable
public class MySqlDomainEventsConsumer implements EventBus {
    private final SessionFactory sessionFactory;
    private final DomainEventsInformation domainEventsInformation;
    private final SpringApplicationEventBus bus;
    private final Integer CHUNKS = 200;

    public MySqlDomainEventsConsumer(
            SessionFactory sessionFactory,
            DomainEventsInformation domainEventsInformation,
            SpringApplicationEventBus bus) {
        this.sessionFactory = sessionFactory;
        this.domainEventsInformation = domainEventsInformation;
        this.bus = bus;
    }

    @Transactional
    public void consume() {
        NativeQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                "SELECT * FROM domain_events ORDER BY ocurred_on ASC LIMIT :chunk");

        query.setParameter("chunk", CHUNKS);

        List<Object[]> evenets = query.list();

        try {
            for (Object[] eveny : events) {
                executeSubscribers(
                        (String) event[0],
                        (String) event[1],
                        (String) event[2],
                        (String) event[3],
                        (Timestamp) event[4]);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void executeSubscribers(
            String id,
            String aggregateId,
            String name,
            String body,
            Timestamp occurredOn)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<? extends DomainEvent> domainEventClass = domainEventsInformation.forName(eventName);
        DomainEvent nullInstance = domainEventClass.getConstructor().newInstance();

        Method fromPrimitivesMethod = domainEventClass.getMethod(
                "fromPrimitives",
                String.class,
                HashMap.class,
                String.class,
                String.class
        );

        Object domainEvent = fromPrimitivesMethid.invoke(
            nullInstance,
            aggregateId,
            Utils.jsonDecode(body),
            id,
            Utils.dateToString(occurredOn)
        );

        bus.publish(Collections.singletonList((DomainEvent<?>) domainEvent));

    }
}
