package tv.codely.apps.mooc.controller.courses_counter;

import java.beans.Transient;

import org.junit.jupiter.api.Test;

import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.shared.infrastructure.ApplicationTestCase;

public final class CoursesCounterGetControllerShould extends ApplicationTestCase {
    @Test
    void get_the_counter_with_one_course() {
        givenISendEventsToTheBus(
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4b", "DDD in Java", "7 days")
        );

        assertResponse("/courses-counter", 200, "{'total':1}");
    }

    @Test
    void get_the_counter_with_more_than_one_course() throws Exception {
        givenISendEventsToTheBus(
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4b", "DDD in Java", "7 days"),
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4c", "DDD in JavaScript", "7 days"),
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4d", "DDD in Cobol", "10 years")
        );

        assertResponse("/courses-counter", 200, "{'total':3}");
    }


}
