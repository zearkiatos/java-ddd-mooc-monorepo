package tv.codely.apps.mooc.controller.courses;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;

@ContextConfiguration(classes = BackofficeBackendApplication.class)
@ActiveProfiles("test")
final class CoursesPutControllerShould extends ApplicationTestCase {
    @Test
    void create_a_valid_course() throws Exception {
        assertRequestWithBody("PUT", "/courses/201c4bb3-b790-492c-9985-9919de9ee5c1",
                "{\"name\": \"The best course\", \"duration\": \"5 hours\"}",
                201
        );
    }
}
