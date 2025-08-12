package tv.codely.mooc.videos.infrastructure;

import java.util.Optional;

import org.springframework.context.annotation.Profile;

import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoRepository;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
@Profile({"test"})

public final class InMemoryVideoRepository implements VideoRepository {

    @Override
    public void save(Video video) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Video> search(VideoId id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

}
