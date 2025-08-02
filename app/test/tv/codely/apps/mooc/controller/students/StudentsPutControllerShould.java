package tv.codely.apps.mooc.controller.students;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import tv.codely.apps.mooc.controller.RequestTestCase;

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
