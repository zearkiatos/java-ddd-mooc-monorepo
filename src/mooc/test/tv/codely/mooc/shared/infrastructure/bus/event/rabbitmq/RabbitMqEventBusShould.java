package tv.codely.mooc.shared.infrastructure.bus.event.rabbitmq;

import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.shared.infrastructure.bus.event.DomainEventSubscriberInformation;
import tv.codely.shared.infrastructure.bus.event.DomainEventSubscribersInformation;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqEventBus;
import tv.codely.mooc.infrastructure.MoocContextInfrastructureTestCase;

@ActiveProfiles({"test", "rabbitmq"})
public final class RabbitMqEventBusShould extends MoocContextInfrastructureTestCase {
    @Autowired
    private RabbitMqEventBus             eventBus;
    @Autowired
    private RabbitMqDomainEventsConsumer consumer;
    @Autowired
    private TestAllWorksOnRabbitMqEventsPublished subscriber;

    // Add RabbitTemplate to manually create queues if needed
    @Autowired(required = false)
    private org.springframework.amqp.rabbit.core.RabbitTemplate rabbitTemplate;

    @Autowired(required = false)
    private org.springframework.amqp.core.AmqpAdmin amqpAdmin;

    @BeforeEach
    protected void setUp() {
        subscriber.hasBeenExecuted = false;

        // Manually create the queue if it doesn't exist
        if (amqpAdmin != null) {
            String queueName = "codelytv.mooc.shared.test_all_works_on_rabbit_mq_events_published";
            try {
                // Create the queue manually
                org.springframework.amqp.core.Queue queue = new org.springframework.amqp.core.Queue(queueName, true, false, false);
                amqpAdmin.declareQueue(queue);

                // Create the exchange manually
                org.springframework.amqp.core.TopicExchange exchange = new org.springframework.amqp.core.TopicExchange("domain_events", true, false);
                amqpAdmin.declareExchange(exchange);

                // Bind the queue to the exchange with multiple routing keys
                // Bind with the queue name (for direct routing)
                org.springframework.amqp.core.Binding queueBinding = org.springframework.amqp.core.BindingBuilder
                    .bind(queue)
                    .to(exchange)
                    .with(queueName);
                amqpAdmin.declareBinding(queueBinding);

                // Bind with the event name (for event-based routing)
                org.springframework.amqp.core.Binding eventBinding = org.springframework.amqp.core.BindingBuilder
                    .bind(queue)
                    .to(exchange)
                    .with("course.1.event.course.created");
                amqpAdmin.declareBinding(eventBinding);

                System.out.println("Queue and binding created successfully: " + queueName);
            } catch (Exception e) {
                System.out.println("Error creating queue: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Set up the subscriber information so RabbitMQ knows what queues to create
        DomainEventSubscribersInformation subscribersInfo = new DomainEventSubscribersInformation(
            new HashMap<Class<?>, DomainEventSubscriberInformation>() {{
                put(TestAllWorksOnRabbitMqEventsPublished.class, new DomainEventSubscriberInformation(
                    TestAllWorksOnRabbitMqEventsPublished.class,
                    Collections.singletonList(CourseCreatedDomainEvent.class)
                ));
            }}
        );

        consumer.withSubscribersInformation(subscribersInfo);

        // Give RabbitMQ some time to process the queue creation
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void publish_and_consume_domain_events_from_rabbitmq() throws Exception {
        CourseCreatedDomainEvent domainEvent = CourseCreatedDomainEventMother.random();

        eventBus.publish(Collections.singletonList(domainEvent));

        consumer.consume();

        eventually(() -> assertTrue(subscriber.hasBeenExecuted));
    }


}
