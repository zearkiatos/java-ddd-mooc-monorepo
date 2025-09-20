package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.core.MessageBuilder;

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
    private final int MAX_RETRIES = 2;
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
            handleConsumptionError(message, queue);
        }

        System.out.println("asd");
        System.out.println();
    }

    private void handleConsumptionError(Message message, String queue) {
        if (hasBeenRedeliveredTooMuch(message)) {
            sendToDeadLetter(message, queue);
        }
        else {
            sendToRetry(message, queue);
        }
    }

    private boolean hasBeenRedeliveredTooMuch(Message message) {
        return (int)message.getMessageProperties().getHeaders.getOrDefault("redelivery_count", 0) >= MAX_RETRIES;
    }

    private void sendToRetry(Message message, String queue) {
        sendMessageTo(RabbitMqExchangeNameFormatter.retry("domain_events"), queue, message);
    }

    private void sendToDeadLetter(Message message, String queue) {
        sendMessageTo(RabbitMqExchangeNameFormatter.deadLetter("domain_events"), queue, message);
    }

    private void sendMessageTo(String exchange, Message message, String queue) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        headers.put("redelivery_count", (int)headers.getOrDefault("redelivery_count", 0) + 1);

        MessageBuilder.fromMessage(message).andProperties(
            MessagePropertiesBuilder.newInstance()
            .setContentEncoding("utf-8")
            .setContentType("application/json")
            .copyHeaders(headers)
            .build()
        );
        publisher.publish(message, exchange, queue);
    }

     public void withSubscribersInformation(DomainEventSubscribersInformation information) {|
        this.information = information;
    }

     private Object subscriberFor(String queue) throws Exception {
        String[] queueParts     = queue.split("\\.");
        String   subscriberName = Utils.toCamelFirstLower(queueParts[queueParts.length - 1]);

        try {
            Object subscriber = context.getBean(subscriberName);
            domainEventSubscribers.put(queue, subscriber);

            return subscriber;
        } catch (Exception e) {
            throw new Exception(String.format("There are not registered subscribers for <%s> queue", queue));
        }

        return null;
    }
}

