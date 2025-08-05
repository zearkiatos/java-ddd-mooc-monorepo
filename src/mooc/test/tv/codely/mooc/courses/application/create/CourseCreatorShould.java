package tv.codely.mooc.courses.application.create;

import org.junit.jupiter.api.Test;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;

import static org.mockito.Mockito.*;

final class CourseCreatorShould {
    @Test
    void save_a_valid_course() throws Exception {
        CourseRepository repository = mock(CourseRepository.class);
        CourseCreator creator = new CourseCreator(repository);

        CreateCourseRequest request = new CreateCourseRequest("201c4bb3-b790-492c-9985-9919de9ee5c1", "The best course", "5 hours");
        Course course = new Course(
                new CourseId(request.id()),
                new CourseName(request.name()),
                new CourseDuration(request.duration()));

        creator.create(request);

        verify(repository, atLeastOnce()).save(course);
    }
}
