package tv.codely.mooc.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

import tv.codely.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;
import tv.codely.mooc.courses.domain.CourseRepository;


import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.shared.infrastructure.InfrastructureTestCase;
import tv.codely.mooc.courses.infrastructure.InMemoryCourseRepository;
import tv.codely.mooc.MoocContextInfrastructureTestCase;

@ContextConfiguration(classes=BackofficeBackendApplication.class)
@SpringBootTest
public abstract class CoursesModuleInfrastructureTestCase extends MoocContextInfrastructureTestCase {

    @MockBean
    private MySqlDomainEventsConsumer mySqlDomainEventsConsumer;

    protected InMemoryCourseRepository inMemoryCourseRepository = new InMemoryCourseRepository();

    @Autowired
    protected CourseRepository mySqlCourseRepository;


}
