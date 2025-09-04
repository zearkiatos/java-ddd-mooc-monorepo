package tv.codely.apps.mooc.controller.courses_counter;

import java.beans.Transient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.apps.shared.controller.ApplicationTestCase;

@ContextConfiguration(classes = MoocBackendApplication.class)
@ActiveProfiles("test")
final class CoursesCounterGetControllerShould extends ApplicationTestCase {

    @Test
    void get_the_counter_with_one_course() throws Exception {
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

    @Test
    void get_the_counter_with_more_than_one_course_having_duplicated_events() throws Exception {
        givenISendEventsToTheBus(
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4b", "DDD in Java", "7 days"),
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4b", "DDD in Java", "7 days"),
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4b", "DDD in Java", "7 days"),
            new CourseCreatedDomainEvent("3642f700-12a1-4563-9bb7-b02f398e5a88", "DDD in PHP", "6 days"),
            new CourseCreatedDomainEvent("3642f700-12a1-4563-9bb7-b02f398e5a88", "DDD in PHP", "6 days"),
            new CourseCreatedDomainEvent("3642f700-12a1-4563-9bb7-b02f398e5a88", "DDD in PHP", "6 days"),
            new CourseCreatedDomainEvent("3642f700-12a1-4563-9bb7-b02f398e5a88", "DDD in PHP", "6 days"),
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4d", "DDD in Cobol", "10 years"),
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4b2c-9f1f-1c4b2c9f1f4d", "DDD in Cobol", "10 years")
        );

        assertResponse("/courses-counter", 200, "{'total':4}");
    }


}
