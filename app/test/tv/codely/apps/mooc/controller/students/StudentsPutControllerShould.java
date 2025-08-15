package tv.codely.apps.mooc.controller.students;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import tv.codely.apps.shared.controller.RequestTestCase;

@ActiveProfiles("test")
final class StudentsPutControllerShould extends RequestTestCase{
    @Test
    void create_a_valid_student() throws Exception {
        String studentId = UUID.randomUUID().toString();
        assertRequestWithBody("PUT", "/students/" + studentId,
                "{\"name\": \"Pedro\", \"surname\": \"Capriles\", \"email\": \"pedro.capriles@example.com\"}",
                201
        );
    }
}
