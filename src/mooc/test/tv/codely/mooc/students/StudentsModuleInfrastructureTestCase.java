package tv.codely.mooc.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

import tv.codely.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;
import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.mooc.students.infrastructure.InMemoryStudentRepository;
import tv.codely.shared.infrastructure.InfrastructureTestCase;
import tv.codely.mooc.MoocContextInfrastructureTestCase;

@ContextConfiguration(classes=BackofficeBackendApplication.class)
@SpringBootTest
public abstract class StudentsModuleInfrastructureTestCase extends MoocContextInfrastructureTestCase {

    @MockBean
    private MySqlDomainEventsConsumer mySqlDomainEventsConsumer;

    @Autowired
    protected StudentRepository mySqlStudentRepository;

    protected InMemoryStudentRepository inMemoryStudentRepository = new InMemoryStudentRepository();
}
