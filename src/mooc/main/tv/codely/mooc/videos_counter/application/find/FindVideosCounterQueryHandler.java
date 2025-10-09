package tv.codely.mooc.videos_counter.application.find;

import tv.codely.shared.domain.bus.query.QueryHandler;
import tv.codely.shared.domain.ServiceInjectable;


@ServiceInjectable
public final class FindVideosCounterQueryHandler implements QueryHandler<FindVideosCounterQuery, VideosCounterResponse> {
    private VideosCounterFinder finder;

    public FindVideosCounterQueryHandler(VideosCounterFinder finder) {
        this.finder = finder;
    }

    @Override
    public VideosCounterResponse handle(FindVideosCounterQuery query) {
        return finder.find();
    }
}
