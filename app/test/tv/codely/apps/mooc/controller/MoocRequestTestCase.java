package tv.codely.apps.mooc.controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MoocBackendApplication.class)
@ActiveProfiles("test")
public abstract class MoocRequestTestCase extends ApplicationTestCase {
}
