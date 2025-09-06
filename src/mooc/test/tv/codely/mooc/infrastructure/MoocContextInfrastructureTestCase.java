package src.mooc.test.tv.codely.mooc.infrastructure;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.UuidGenerator;
import tv.codely.shared.domain.bus.event.EventBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import javax.transaction.Transactional;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.shared.infrastructure.InfrastructureTestCase;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;
import tv.codely.mooc.courses_counter.application.increment.CoursesCounterIncrementer;

@ContextConfiguration(classes = MoocBackendApplication.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public abstract class MoocContextInfrastructureTestCase extends InfrastructureTestCase {
    @MockBean
    protected MySqlDomainEventsConsumer mySqlDomainEventsConsumer;

    @Autowired
    private CoursesCounterRepository coursesCounterRepository;

    @Autowired
    protected CoursesCounterIncrementer coursesCounterIncrementer;

    @MockBean
    protected SessionFactory sessionFactory;

    @MockBean
    protected UuidGenerator uuidGenerator;

    @Autowired
    protected CoursesCounterIncrementer coursesCounterIncrementer;

    @Autowired
    protected EventBus eventBus;
}
