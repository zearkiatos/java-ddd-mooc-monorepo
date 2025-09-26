package tv.codely.mooc.courses_counter.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tv.codely.mooc.courses_counter.CoursesCounterModuleUnitTestCase;
import tv.codely.mooc.courses_counter.application.find.CoursesCounterFinder;

public final class FindCoursesCounterQueryHandlerShould extends CoursesCounterModuleUnitTestCase {
    FindCoursesCounterCounterQueryHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new FindCoursesCounterCounterQueryHandler(new CoursesCounterFinder(repository));
    }

    @Test
    void it_should_find_an_existing_courses_counter() {
        CoursesCounter counter = CoursesCounterMother.random();
        FindCourseCounterQuery query = new FindCoursesCounterQuery();
        CoursesCounterResponse response = CoursesCounterResponseMother.create(counter.total().value());

        shouldSearch(counter);

        assertEquals(response, handler.handle(query));
    }

}
