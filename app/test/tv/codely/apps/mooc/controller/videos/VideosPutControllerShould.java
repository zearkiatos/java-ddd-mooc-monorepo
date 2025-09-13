package tv.codely.apps.mooc.controller.videos;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;

@ContextConfiguration(classes = MoocBackendApplication.class)
@ActiveProfiles("test")
final class VideosPutControllerShould extends ApplicationTestCase {
    @Test
    void create_a_valid_video() throws Exception {
        String videoId = UUID.randomUUID().toString();
        assertRequestWithBody("PUT", "/videos/" + videoId,
                "{\"title\": \"Video Title\", \"url\": \"http://example.com\", \"description\": \"Video Description\"}",
                201
        );
    }
}
