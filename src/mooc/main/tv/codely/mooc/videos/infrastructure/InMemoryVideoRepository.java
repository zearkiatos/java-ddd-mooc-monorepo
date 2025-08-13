package tv.codely.mooc.videos.infrastructure;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.context.annotation.Profile;

import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoRepository;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
@Profile({"test"})
public final class InMemoryVideoRepository implements VideoRepository {
    private HashMap<String, Video> videos = new HashMap<>();
    @Override
    public void save(Video video) {
        videos.put(video.id().value(), video);
    }

    @Override
    public Optional<Video> search(VideoId id) {
        return Optional.ofNullable(videos.get(id.value()));
    }

}
