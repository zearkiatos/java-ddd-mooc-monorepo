package tv.codely.backoffice.courses.application.search_all;

import tv.codely.backoffice.courses.application.search_all.AllBackofficeCoursesSearcher;
import tv.codely.shared.domain.bus.query.QueryHandler;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;

@ServiceInjectable
public final class SearchAllBackofficeCoursesQueryHandler implements QueryHandler<SearchAllBackofficeCoursesQuery, BackofficeCoursesResponse> {
    private final AllBackofficeCoursesSearcher searcher;

    public SearchAllBackofficeCoursesQueryHandler(AllBackofficeCoursesSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public BackofficeCoursesResponse handle(SearchAllBackofficeCoursesQuery query) {
        return searcher.search();
    }
}
