package src.shared.test.tv.codely.shared.infrastructure.bus.event.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import javax.transaction.Transactional;

import tv.codely.mooc.MoocContextInfrastructureTestCase;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlEventBus;

@Transactional
class MySqlEventBusShould extends MoocContextInfrastructureTestCase {
    @Autowired
    private MySqlEventBus eventBus;

    @Test
    void publish_and_consume_domain_events_from_mysql() {
        CourseCreatedDomainEvent domainEvent = CourseCreatedDomainEventMother.random();

        eventBus.publish(Collections.singletonList(domainEvent));

        consumer.consume();
    }
}
