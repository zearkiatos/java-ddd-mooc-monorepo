package tv.codely.mooc.courses.infrastructure;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;



final class InMemoryCourseRepositoryShould {
    @Test
    void save_a_valid_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        Course course = new Course(new CourseId("201c4bb3-b790-492c-9985-9919de9ee5c1"), new CourseName("The best course"), new CourseDuration("5 hours"));

        repository.save(course);

    }

    @Test
    void search_an_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        Course course = new Course(new CourseId("201c4bb3-b790-492c-9985-9919de9ee5c1"), new CourseName("The best course"), new CourseDuration("5 hours"));

        repository.save(course);

        Assert.assertEquals(Optional.of(course), repository.search(course.id().toString()));

    }

    @Test
    void not_find_a_non_existing_course() throws Exception {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        Assert.assertFalse(repository.search("non-existing-id").isPresent());
    }

}
