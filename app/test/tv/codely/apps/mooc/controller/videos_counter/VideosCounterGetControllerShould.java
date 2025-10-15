package tv.codely.apps.mooc.controller.videos_counter;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;
import tv.codely.mooc.videos_counter.application.increment.VideosCounterIncrementer;
import tv.codely.mooc.videos.domain.VideoCreatedDomainEvent;

@ContextConfiguration(classes = MoocBackendApplication.class)
@ActiveProfiles("test")
final class VideosCounterGetControllerShould extends ApplicationTestCase {
    private VideosCounterIncrementer incrementer;

    @Test
    void get_the_counter_with_one_video() throws Exception {
        givenISendEventsToTheBus(
                new VideoCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4b", "Java course", "This a video about DDD", "http://example.com/video.mp4")
        );

        assertResponse("/videos-counter", 200, "{'total':1}");
    }
}
