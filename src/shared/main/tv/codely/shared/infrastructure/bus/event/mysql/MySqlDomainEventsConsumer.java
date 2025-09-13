package tv.codely.shared.infrastructure.bus.event.mysql;

import org.hibernate.query.NativeQuery;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.sql.Timestamp;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;

import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.Utils;
import tv.codely.shared.infrastructure.bus.event.DomainEventsInformation;
import tv.codely.shared.infrastructure.bus.event.spring.SpringApplicationEventBus;
import tv.codely.shared.infrastructure.bus.event.DomainEventsInformation;

@ServiceInjectable
@Profile({"local", "test"})
public class MySqlDomainEventsConsumer {
    private final SessionFactory sessionFactory;
    private final DomainEventsInformation domainEventsInformation;
    private final SpringApplicationEventBus bus;
    private final Integer CHUNKS = 200;
    private Boolean isStopped = false;

    public MySqlDomainEventsConsumer(
            SessionFactory sessionFactory,
            DomainEventsInformation domainEventsInformation,
            SpringApplicationEventBus bus) {
        this.sessionFactory = sessionFactory;
        this.domainEventsInformation = domainEventsInformation;
        this.bus = bus;
    }

    public void stop() {
        isStopped = true;
    }

    @Transactional
    public void consume() {
        while (!isStopped) {
            NativeQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                    "SELECT * FROM domain_events ORDER BY occurred_on ASC LIMIT :chunk");

            query.setParameter("chunk", CHUNKS);

            List<Object[]> events = query.list();

            try {
                for (Object[] event : events) {
                    executeSubscribers(
                            (String) event[0],
                            (String) event[1],
                            (String) event[2],
                            (String) event[3],
                            (Timestamp) event[4]);
                    System.out.println("Uuid" + (String) event[0]);
                    System.out.println("Topic name" + (String) event[2]);
                    System.out.println("Processed event " + (String) event[3]);
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
                    | InstantiationException e) {
                e.printStackTrace();
            }

            sessionFactory.getCurrentSession().clear();
        }
    }

    private void executeSubscribers(
            String id,
            String aggregateId,
            String eventName,
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
                String.class);

        Object domainEvent = fromPrimitivesMethod.invoke(
                nullInstance,
                aggregateId,
                Utils.jsonDecode(body),
                id,
                Utils.dateToString(occurredOn));

        bus.publish(Collections.singletonList((DomainEvent<?>) domainEvent));

    }
}
