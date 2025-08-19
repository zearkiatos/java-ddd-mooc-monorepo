package tv.codely.mooc.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.mooc.students.infrastructure.InMemoryStudentRepository;
import tv.codely.shared.infrastructure.InfrastructureTestCase;
import tv.codely.mooc.MoocContextInfrastructureTestCase;

@ContextConfiguration(classes=MoocBackendApplication.class)
@SpringBootTest
public abstract class StudentsModuleInfrastructureTestCase extends MoocContextInfrastructureTestCase {
    protected InMemoryStudentRepository inMemoryStudentRepository = new InMemoryStudentRepository();

    @Autowired
    protected StudentRepository mySqlStudentRepository;
}
