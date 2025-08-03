package tv.codely.mooc.courses.application.create;

import org.junit.jupiter.api.Test;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;

import static org.mockito.Mockito.*;

final class CourseCreatorShould {
    @Test
    void save_a_valid_course() throws Exception {
        CourseRepository repository = mock(CourseRepository.class);
        CourseCreator creator = new CourseCreator(repository);

        Course course = new Course("201c4bb3-b790-492c-9985-9919de9ee5c1", "The best course", "5 hours");

        creator.create(new CreateCourseRequest(course.id(), course.name(), course.duration()));

        verify(repository, atLeastOnce()).save(course);
    }
}
