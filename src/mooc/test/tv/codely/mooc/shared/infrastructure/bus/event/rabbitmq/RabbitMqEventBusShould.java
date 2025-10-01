package tv.codely.mooc.shared.infrastructure.bus.event.rabbitmq;

import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.shared.infrastructure.bus.event.DomainEventSubscriberInformation;
import tv.codely.shared.infrastructure.bus.event.DomainEventSubscribersInformation;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqEventBus;
import tv.codely.mooc.infrastructure.MoocContextInfrastructureTestCase;


public final class RabbitMqEventBusShould extends MoocContextInfrastructureTestCase {
    @MockBean
    private RabbitMqEventBus             eventBus;
    @MockBean
    private RabbitMqDomainEventsConsumer consumer;
    @MockBean
    private TestAllWorksOnRabbitMqEventsPublished subscriber;

    @BeforeEach
    protected void setUp() {
        subscriber.hasBeenExecuted = false;

        consumer.withSubscribersInformation(
            new DomainEventSubscribersInformation(
                new HashMap<Class<?>, DomainEventSubscriberInformation>() {{
                    put(TestAllWorksOnRabbitMqEventsPublished.class, new DomainEventSubscriberInformation(
                        TestAllWorksOnRabbitMqEventsPublished.class,
                        Collections.singletonList(CourseCreatedDomainEvent.class)
                    ));
                }}
            )
        );
    }

    @Test
    void publish_and_consume_domain_events_from_rabbitmq() throws Exception {
        CourseCreatedDomainEvent domainEvent = CourseCreatedDomainEventMother.random();

        eventBus.publish(Collections.singletonList(domainEvent));

        consumer.consume();

        eventually(() -> assertTrue(subscriber.hasBeenExecuted));
    }


}
