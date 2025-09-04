package src.mooc.test.tv.codely.mooc.infrastructure;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.shared.infrastructure.InfrastructureTestCase;


@ContextConfiguration(classes = MoocBackendApplication.class)
@SpringBootTest
public abstract class MoocContextInfrastructureTestCase extends InfrastructureTestCase {
}
