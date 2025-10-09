package tv.codely.mooc.videos_counter.application.find;

import tv.codely.mooc.videos_counter.domain.VideosCounter;
import tv.codely.mooc.videos_counter.domain.VideosCounterRepository;
import tv.codely.mooc.videos_counter.domain.VideosCounterNotInitialized;
import tv.codely.shared.domain.ServiceInjectable;


@ServiceInjectable
public final class VideosCounterFinder {
    private VideosCounterRepository repository;

    public VideosCounterFinder(VideosCounterRepository repository) {
        this.repository = repository;
    }

    public VideosCounterResponse find() {
        VideosCounter videosCounter = repository.search().orElseGet(() -> {
            throw new VideosCounterNotInitialized();
        });

        return new VideosCounterResponse(videosCounter.total().value());
    }
}
