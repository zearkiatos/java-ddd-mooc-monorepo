package tv.codely.mooc.videos_counter.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tv.codely.mooc.videos_counter.application.find.FindVideosCounterQueryHandler;
import tv.codely.mooc.videos_counter.application.find.VideosCounterFinder;
import tv.codely.mooc.courses_counter.application.find.FindCoursesCounterQueryHandler;
import tv.codely.mooc.videos_counter.VideosCounterModuleUnitTestCase;
import tv.codely.mooc.videos_counter.domain.VideosCounter;
import tv.codely.mooc.videos_counter.domain.VideosCounterMother;
import tv.codely.mooc.videos_counter.domain.VideosCounterNotInitialized;
import tv.codely.mooc.videos_counter.application.find.FindVideosCounterQuery;
import tv.codely.mooc.videos_counter.application.find.VideosCounterResponse;
import tv.codely.mooc.videos_counter.application.find.VideosCounterResponseMother;

public final class FindVideosCounterQueryHandlerShould extends VideosCounterModuleUnitTestCase {
    FindVideosCounterQueryHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new FindVideosCounterQueryHandler(new VideosCounterFinder(repository));
    }

    @Test
    void it_should_find_an_existing_videos_counter() {
        VideosCounter          counter  = VideosCounterMother.random();
        FindVideosCounterQuery query    = new FindVideosCounterQuery();
        VideosCounterResponse  response = VideosCounterResponseMother.create(counter.total().value());

        shouldSearch(counter);

        assertEquals(response, handler.handle(query));
    }

    @Test
    void it_should_throw_an_exception_when_videos_counter_does_not_exists() {
        FindVideosCounterQuery query = new FindVideosCounterQuery();

        shouldSearch();

        assertThrows(VideosCounterNotInitialized.class, () -> handler.handle(query));
    }

}
