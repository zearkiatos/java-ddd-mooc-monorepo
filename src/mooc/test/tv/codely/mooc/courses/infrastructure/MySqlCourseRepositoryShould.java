package tv.codely.mooc.courses.infrastructure;

import tv.codely.mooc.courses.CoursesModuleInfrastructureTestCase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import javax.transaction.Transactional;
import org.springframework.test.context.ActiveProfiles;

import tv.codely.mooc.courses.CoursesModuleInfrastructureTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.courses.domain.CourseMother;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Transactional
@ActiveProfiles("local")
final class MySqlCourseRepositoryShould extends CoursesModuleInfrastructureTestCase {
    @Test
    void save_a_valid_course() {
        for (Course course: courses()) {
            mySqlCourseRepository.save(course);
        }
    }

    @Test
    void search_an_existing_course() {

        for(Course course: courses()) {
            mySqlCourseRepository.save(course);
            assertEquals(Optional.of(course), mySqlCourseRepository.search(course.id()));
        }
    }

    @Test
    void not_find_a_non_existing_course() throws Exception {
        for(Course course: courses()) {
            assertFalse(mySqlCourseRepository.search(course.id()).isPresent());
        }
    }

    private List<? extends Course> courses() {
        return Arrays.asList(
            CourseMother.random()
        );
    }
}
