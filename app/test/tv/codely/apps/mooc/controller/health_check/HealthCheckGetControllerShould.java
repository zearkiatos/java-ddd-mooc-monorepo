package tv.codely.apps.mooc.controller.health_check;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;

@ContextConfiguration(classes = MoocBackendApplication.class)
final class HealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    void check_health_check_is_working() throws Exception {
        assertResponse("/health-check", 200, "{'status':'ok', 'message':'Mooc Backend ✅'}");
    }
}
