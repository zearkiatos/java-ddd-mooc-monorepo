package tv.codely.apps.mooc.controller.videos_counter;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;

@ContextConfiguration(classes=MoocBackendApplication.class)
@ActiveProfiles("test")
final class VideosCounterGetControllerShould extends ApplicationTestCase {
    private VideoscCounterIncrementer incrementer;

    @Test
    void get_the_counter_with_one_video() throws Exception {
        assertResponse("/videos-counter", 200, "{'total':1}");
    }
}
