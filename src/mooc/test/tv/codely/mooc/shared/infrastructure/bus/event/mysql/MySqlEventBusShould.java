package tv.codely.mooc.shared.infrastructure.bus.event.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import javax.transaction.Transactional;

import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.mooc.MoocContextInfrastructureTestCase;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlEventBus;
import tv.codely.shared.domain.UuidGenerator;
import tv.codely.mooc.courses.domain.CourseRepository;


@Transactional
class MySqlEventBusShould extends MoocContextInfrastructureTestCase {

    
    @Test
    void publish_and_consume_domain_events_from_mysql() {
        CourseCreatedDomainEvent domainEvent = CourseCreatedDomainEventMother.random();

        eventBus.publish(Collections.singletonList(domainEvent));

        consumer.consume();
    }
}
