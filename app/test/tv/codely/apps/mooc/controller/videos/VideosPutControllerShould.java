package tv.codely.apps.mooc.controller.videos;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import tv.codely.apps.mooc.controller.RequestTestCase;

@ActiveProfiles("test")
final class VideosPutControllerShould extends RequestTestCase {
    @Test
    void create_a_valid_video() throws Exception {
        String videoId = UUID.randomUUID().toString();
        assertRequestWithBody("PUT", "/videos/" + videoId,
                "{\"title\": \"Video Title\", \"url\": \"http://example.com\", \"description\": \"Video Description\"}",
                201
        );
    }
}
