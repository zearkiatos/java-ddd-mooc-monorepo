package tv.codely.mooc.videos.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

import tv.codely.shared.domain.bus.event.DomainEvent;

public final class VideoCreatedDomainEvent extends DomainEvent<VideoCreatedDomainEvent> {
    private final String title;
    private final String description;
    private final String url;

    public VideoCreatedDomainEvent(String aggregateId, String title, String description, String url) {
        super(aggregateId);

        this.title       = title;
        this.description = description;
        this.url         = url;
    }

    public VideoCreatedDomainEvent(
        String aggregateId,
        String eventId,
        String occurredOn,
        String title,
        String description,
        String url
    ) {
        super(aggregateId, eventId, occurredOn);

        this.title       = title;
        this.description = description;
        this.url         = url;
    }

    public VideoCreatedDomainEvent() {
        super(null);

        this.title       = null;
        this.description = null;
        this.url         = null;
    }

    @Override
    public String eventName() {
        return "video.created";
    }

    @Override
    public java.util.HashMap<String, Serializable> toPrimitives() {
        return new java.util.HashMap<String, java.io.Serializable>() {{
            put("title", title);
            put("description", description);
            put("url", url);
        }};
    }

    @Override
    public VideoCreatedDomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn
    ) {
        return new VideoCreatedDomainEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("title"),
            (String) body.get("description"),
            (String) body.get("url")
        );
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VideoCreatedDomainEvent that = (VideoCreatedDomainEvent) o;
        return this.title.equals(that.title) &&
                this.description.equals(that.description) &&
                this.url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, url);
    }
}
