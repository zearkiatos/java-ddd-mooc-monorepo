package tv.codely.apps.mooc.controller.students;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import java.util.UUID;
import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;

@ContextConfiguration(classes = MoocBackendApplication.class)
@ActiveProfiles("test")
final class StudentsPutControllerShould extends ApplicationTestCase{
    @Test
    void create_a_valid_student() throws Exception {
        String studentId = UUID.randomUUID().toString();
        assertRequestWithBody("PUT", "/students/" + studentId,
                "{\"name\": \"Pedro\", \"surname\": \"Capriles\", \"email\": \"pedro.capriles@example.com\"}",
                201
        );
    }
}
