package tv.codely.backoffice.courses.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import tv.codely.backoffice.BackofficeContextInfrastructureTestCase;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseCriteriaMother;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.backoffice.courses.domain.BackofficeCourseMother;



final class InMemoryCacheBackofficeCourseRepositoryShould extends BackofficeContextInfrastructureTestCase {
    private InMemoryCacheBackofficeCourseRepository repository;
    private BackofficeCourseRepository innerRepository;

    @BeforeEach
    protected void setUp() {
        innerRepository = mock(BackofficeCourseRepository.class);
        repository = new InMemoryCacheBackofficeCourseRepository(innerRepository);
    }

    @Test
    void save_a_course() {
        BackofficeCourse course = BackofficeCourseMother.random();

        repository.save(course);

        shouldHaveSaved(course);
    }

    @Test
    void search_all_existing_courses() {
        BackofficeCourse course = BackofficeCourseMother.random();
        BackofficeCourse anotherCourse = BackofficeCourseMother.random();
        List<BackofficeCourse> courses = Arrays.asList(course, anotherCourse);

        shouldSearchAll(courses);

        assertThat(courses, containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    void search_all_existing_courses_without_hitting_the_inner_repository_the_second_time() {
        BackofficeCourse course = BackofficeCourseMother.random();
        BackofficeCourse anotherCourse = BackofficeCourseMother.random();
        List<BackofficeCourse> courses = Arrays.asList(course, anotherCourse);

        shouldSearchAll(courses);

        assertThat(courses, containsInAnyOrder(repository.searchAll().toArray()));
        assertThat(courses, containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    void search_courses_using_a_criteria() {
        BackofficeCourse matchingCourse = BackofficeCourseMother.create("DDD in Java", "3 days");
        BackofficeCourse anotherMatchingCourse = BackofficeCourseMother.create("DDD in Typescript", "2.5 days");
        List<BackofficeCourse> matchingCourses = Arrays.asList(matchingCourse, anotherMatchingCourse);

        Criteria criteria = BackofficeCourseCriteriaMother.nameAndDurationContains("DDD", "days");

        shouldSearchMatching(criteria, matchingCourses);

        assertThat(matchingCourses, containsInAnyOrder(repository.matching(criteria).toArray()));
    }

    @Test
    void search_courses_using_a_criteria_without_hitting_the_inner_repository_the_second_time() {
        BackofficeCourse matchingCourse = BackofficeCourseMother.create("DDD in Java", "3 days");
        BackofficeCourse anotherMatchingCourse = BackofficeCourseMother.create("DDD in Typescript", "2.5 days");
        List<BackofficeCourse> matchingCourses = Arrays.asList(matchingCourse, anotherMatchingCourse);

        Criteria criteria = BackofficeCourseCriteriaMother.nameAndDurationContains("DDD", "days");

        shouldSearchMatching(criteria, matchingCourses);

        assertThat(matchingCourses, containsInAnyOrder(repository.matching(criteria).toArray()));
        assertThat(matchingCourses, containsInAnyOrder(repository.matching(criteria).toArray()));
    }

}
