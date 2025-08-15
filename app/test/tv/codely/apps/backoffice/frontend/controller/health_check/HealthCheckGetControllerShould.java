package tv.codely.apps.backoffice.frontend.controller.health_check;

import org.junit.jupiter.api.Test;

import tv.codely.apps.shared.controller.RequestTestCase;

final class HealthCheckGetControllerShould extends RequestTestCase {
    @Test
    void check_health_check_is_working() throws Exception {
        assertResponse("/health-check", 200, "{'status':'ok', 'message':'Backoffice Frontend ✅'}");
    }
}
