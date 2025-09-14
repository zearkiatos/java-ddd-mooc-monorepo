package tv.codely.shared.infrastructure.bus.event;

import java.io.Serializable;
import java.util.HashMap;

import tv.codely.shared.domain.Utils;
import tv.codely.shared.domain.bus.event.DomainEvent;

public final class DomainEventJsonSerializer {
    public static String serializer(DomainEvent<?> domainEvent) {
        HashMap<String, Serializable> attributes = domainEvent.toPrimitives();
        attributes.put("id", domainEvent.aggregateId());

        return Utils.jsonEncode(new HashMap<String, Serializable>(){{
            put("data", new HashMap<String, Serializable>() {{
                put("id", domainEvent.eventId());
                put("type", domainEvent.eventName());
                put("occurred_on", domainEvent.occurredOn());
                put("attributes", attributes);
            }});
            put("meta", new HashMap<String, Serializable>());
        }});

    }
}
