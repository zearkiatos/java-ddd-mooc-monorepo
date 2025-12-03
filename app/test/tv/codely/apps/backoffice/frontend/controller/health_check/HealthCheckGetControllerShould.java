package tv.codely.apps.backoffice.frontend.controller.health_check;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.apps.backoffice.frontend.BackofficeFrontendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;

@ContextConfiguration(classes = MoocFrontendApplication.class)
final class HealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    void check_health_check_is_working() throws Exception {
        assertResponse("/health-check", 200, "{'status':'ok', 'message':'Backoffice Frontend ✅'}");
    }
}
