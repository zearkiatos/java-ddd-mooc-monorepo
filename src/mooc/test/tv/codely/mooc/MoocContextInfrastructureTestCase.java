package tv.codely.mooc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.infrastructure.InfrastructureTestCase;

@ContextConfiguration(classes = BackofficeBackendApplication.class)
@SpringBootTest
public abstract class MoocContextInfrastructureTestCase extends InfrastructureTestCase {
    @Autowired
    CourseRepository courseRepository;
}
