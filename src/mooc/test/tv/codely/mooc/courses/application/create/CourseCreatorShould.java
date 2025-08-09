package tv.codely.mooc.courses.application.create;

import org.junit.jupiter.api.Test;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseMother;
import tv.codely.mooc.courses.domain.CourseRepository;

import static org.mockito.Mockito.*;

final class CourseCreatorShould {
    @Test
    void save_a_valid_course() throws Exception {
        CourseRepository repository = mock(CourseRepository.class);
        CourseCreator creator = new CourseCreator(repository);

        CreateCourseRequest request = CreateCourseRequestMother.random();
        Course course = CourseMother.fromRequest(request);

        creator.create(request);

        verify(repository, atLeastOnce()).save(course);
    }
}
