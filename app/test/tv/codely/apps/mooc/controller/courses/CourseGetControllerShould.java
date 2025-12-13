package tv.codely.apps.mooc.controller.courses;

import tv.codely.apps.mooc.controller.MoocRequestTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

final class CourseGetControllerShould extends MoocRequestTestCase {
    @Test
    void find_an_existing_course() throws Exception {
        String id   = "99ad55f5-6eab-4d73-b383-c63268e251e8";
        String body = "{\"name\": \"The best course\", \"duration\": \"5 hours\"}";

        givenThereIsACourse(id, body);

        assertResponse(String.format("/courses/%s", id), 200, body);
    }

    private void givenThereIsACourse(String id, String body) throws Exception {
        assertRequestWithBody("PUT", String.format("/courses/%s", id), body, 201);
    }

    @Test
    void not_find_a_non_existing_course() throws Exception {
        String id = "c56f3f2e-5dcb-4f12- ninth-8d1e4f3c4a2b";
        String body = "{\"error_code\": \"course_not_exist\", \"message\": \"The course <" + id + "> does not exist\"}";

        assertResponse(String.format("/courses/%s", id), 404, body);
    }

    private void givenThereIsACourse(String id, String body) throws Exception {
        assertRequestWithBody("PUT", String.format("/courses/%s", id), body, 201);
    }

}
