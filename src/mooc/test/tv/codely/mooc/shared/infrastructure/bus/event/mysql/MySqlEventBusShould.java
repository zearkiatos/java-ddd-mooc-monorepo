package tv.codely.mooc.shared.infrastructure.bus.event.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.when;

import tv.codely.mooc.videos.domain.VideoRepository;
import tv.codely.mooc.videos_counter.domain.VideosCounterRepository;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;


import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.UUID;

import javax.transaction.Transactional;

import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.mooc.MoocContextInfrastructureTestCase;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlEventBus;
import tv.codely.shared.domain.UuidGenerator;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;
import tv.codely.shared.domain.bus.event.EventBus;

@Transactional
class MySqlEventBusShould extends MoocContextInfrastructureTestCase {

    @Autowired
    private EventBus eventBus;

    @MockBean
    private MySqlDomainEventsConsumer consumer;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private CoursesCounterRepository coursesCounterRepository;

    @MockBean
    private UuidGenerator uuidGenerator;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private VideoRepository videoRepository;

    @MockBean
    private VideosCounterRepository videosCounterRepository;


    @BeforeEach
    void setUp() {
        // Configure the mocked UuidGenerator to return valid UUIDs
        when(uuidGenerator.generate()).thenReturn(UUID.randomUUID().toString());
    }

    @Test
    void publish_and_consume_domain_events_from_mysql() {
        CourseCreatedDomainEvent domainEvent = CourseCreatedDomainEventMother.random();

        eventBus.publish(Collections.singletonList(domainEvent));

        consumer.consume();
    }
}
