package tv.codely.mooc.videos.infrastructure;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import tv.codely.mooc.videos.VideosModuleInfrastructureTestCase;
import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoIdMother;
import tv.codely.mooc.videos.domain.VideoMother;

final class InMemoryVideoRepositoryShould extends VideosModuleInfrastructureTestCase {
    @Test
    void save_a_valid_video() {
        InMemoryVideoRepository repository = new InMemoryVideoRepository();

        Video video = VideoMother.random();

        repository.save(video);
    }

    @Test
    void search_an_existing_video() {
        InMemoryVideoRepository repository = new InMemoryVideoRepository();

        Video video = VideoMother.random();

        repository.save(video);

        Assert.assertEquals(Optional.of(video), repository.search(video.id()));
    }

    @Test
    void not_find_a_non_existing_video() throws Exception {
        InMemoryVideoRepository repository = new InMemoryVideoRepository();

        Assert.assertFalse(repository.search(VideoIdMother.random()).isPresent());
    }
}
