package tv.codely.mooc.videos.application.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tv.codely.mooc.videos.VideosModuleUnitTestCase;
import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoMother;

final class VideoCreatorShould extends VideosModuleUnitTestCase {
    private VideoCreator creator;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        creator = new VideoCreator(repository);
    }

    @Test
    void save_a_valid_video() throws Exception {
        CreateVideoRequest request = CreateVideoRequestMother.random();
        Video video = VideoMother.fromRequest(request);

        creator.create(request);

        shouldHaveSaved(video);
    }
}
