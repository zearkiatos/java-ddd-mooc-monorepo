package tv.codely.analytics.domain_events.application.store;

import tv.codely.analytics.domain_events.domain.AnalyticsDomainEvent;
import tv.codely.analytics.domain_events.domain.AnalyticsDomainEventAggregateId;
import tv.codely.analytics.domain_events.domain.AnalyticsDomainEventBody;
import tv.codely.analytics.domain_events.domain.AnalyticsDomainEventId;
import tv.codely.analytics.domain_events.domain.AnalyticsDomainEventName;
import tv.codely.analytics.domain_events.domain.DomainEventsRepository;

public final class DomainEventStorer {
    private DomainEventsRepository repository;

    public DomainEventStorer(DomainEventsRepository repository) {
        this.repository = repository;
    }

    public void store(
        AnalyticsDomainEventId id,
        AnalyticsDomainEventAggregateId aggregateId,
        AnalyticsDomainEventName name,
        AnalyticsDomainEventBody body
    ) {
        AnalyticsDomainEvent domainEvent = new AnalyticsDomainEvent(id, aggregateId, name, body);

        repository.save(domainEvent);
    }
}
