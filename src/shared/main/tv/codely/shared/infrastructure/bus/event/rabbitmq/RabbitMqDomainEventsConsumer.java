package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.ApplicationContext;

import tv.codely.shared.infrastructure.bus.event.DomainEventJsonDeserializer;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqPublisher;
import tv.codely.shared.infrastructure.bus.event.DomainEventSubscribersInformation;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.ServiceInjectable;


@Serviceinjectable
public final class RabbitMqDomainEventsConsumer {
    private final String CONSUMER_NAME = "domain_events_consumer";
    private final DomainEventJsonDeserializer   deserializer;
    private final RabbitMqPublisher publisher;
    private final HashMap<String, Object> domainEventsSubscribers = new HashMap<>();
    RabbitListenerEndpointRegistry registry;
    DomainSubscribersInformation information;

    public RabbitMqDomainEventsConsumer(
        RabbitListenerEndpointRegistry registry,
        DomainEventSubscribersInformation information,
        DomainEventJsonDeserializer deserializer,
        ApplicationContext context,
        RabbitMqPublisher publisher
    ) {
        this.registry = registry;
        this.information = information;
        this.deserializer = deserializer;
        this.context = context;
        this.publisher = publisher;
    }

    public void consume() {
        AbstractMessageListenerContainer container = (AbstractMessageListenerContainer) registry.getListenerContainer(CONSUMER_NAME);
        container.addQueueNames(information.rabbitMqQueueNames());
        container.start();
    }

    @RabbitListener(id = CONSUMER_NAME, auto_startup = "false")
    public void consumer(Message message) throws Exception {
        String serializedMessage = new String(message.getBody());
        DomainEvent<?> domainEvent = deserializer.deserialize(serializedMessage);
        String queue = message.getMessageProperties().getConsumerQueue();

        System.out.println(serializedMessage);
        System.out.println(message.getMessageProperties().getConsumerQueue());

        Object subscriber = domainEventsSubscribers.containsKey(queue)
                  ? domainEventSubscribers.get(queue)
                  : subscriberFor(queue);

        Method subscriberOnMethod = subscriber.getClass().getMethod("on", domainEvent.getClass());

        try {
            subscriberOnMethod.invoke(subscriber, domainEvent);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException error) {
            throw new Exception(String.format("The subscriber <%s> should implement the method 'on' listening the domain event <%s>", queue, domainEvent.eventName()));
        }
        catch(Exception error) {
            // KAPACHAO!!!
        }

        System.out.println("asd");
        System.out.println();
    }

     public void withSubscribersInformation(DomainEventSubscribersInformation information) {
        this.information = information;
    }

     private Object subscriberFor(String queue) {
        String[] queueParts     = queue.split("\\.");
        String   subscriberName = Utils.toCamelFirstLower(queueParts[queueParts.length - 1]);

        try {
            Object subscriber = context.getBean(subscriberName);
            domainEventSubscribers.put(queue, subscriber);

            return subscriber;
        } catch (Exception e) {
            System.out.println(String.join("\n", context.getBeanDefinitionNames()));
            System.out.println(e.getMessage());
        }

        return null;
    }
}

