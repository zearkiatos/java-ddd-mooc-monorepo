package tv.codely.mooc.videos_counter.infrastructure;

import org.springframework.context.annotation.Profile;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.mooc.videos_counter.domain.VideosCounter;
import tv.codely.mooc.videos_counter.domain.VideosCounterRepository;

@ServiceInjectable
@Profile("test")
public class InMemoryVideosCounterRepository implements VideosCounterRepository {
    private VideosCounter counter;

    @Override
    public void save(VideosCounter counter) {
        this.counter = counter;
    }

    @Override
    public Optional<VideosCounter> search() {
        return Optional.ofNullable(counter);
    }

    public void clear() {
        this.counter = null;
    }
}
