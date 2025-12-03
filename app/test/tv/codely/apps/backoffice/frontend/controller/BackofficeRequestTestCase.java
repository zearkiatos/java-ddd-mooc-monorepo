package tv.codely.apps.backoffice.frontend.controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tv.codely.apps.backoffice.frontend.BackofficeFrontendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MoocFrontendApplication.class)
@ActiveProfiles("test")
public abstract class BackofficeRequestTestCase extends ApplicationTestCase {
}
