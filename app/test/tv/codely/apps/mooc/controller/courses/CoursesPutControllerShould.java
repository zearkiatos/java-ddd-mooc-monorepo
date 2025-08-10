package tv.codely.apps.mooc.controller.courses;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import tv.codely.apps.mooc.controller.RequestTestCase;

@ActiveProfiles("test")
final class CoursesPutControllerShould extends RequestTestCase {
    @Test
    void create_a_valid_course() throws Exception {
        assertRequestWithBody("PUT", "/courses/201c4bb3-b790-492c-9985-9919de9ee5c1",
                "{\"name\": \"The best course\", \"duration\": \"5 hours\"}",
                201
        );
    }
}
