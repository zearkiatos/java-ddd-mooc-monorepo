package tv.codely.mooc.videos_counter.application.increment;

import tv.codely.shared.domain.UuidGenerator;
import tv.codely.mooc.videos_counter.domain.VideosCounter;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos_counter.domain.VideosCounterRepository;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
public final class VideosCounterIncrementer {
    private VideosCounterRepository repository;
    private UuidGenerator uuidGenerator;

    public VideosCounterIncrementer(VideosCounterRepository repository, UuidGenerator uuidGenerator) {
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
    }

    public void increment(VideoId id) {
        VideosCounter counter = repository.search().orElseGet(() -> VideosCounter.initialize(uuidGenerator.generate()));

        if (!counter.hasIncremented(id)) {
            counter.increment(id);
            repository.save(counter);
        }
    }
}
