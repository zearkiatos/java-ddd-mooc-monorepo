package tv.codely.backoffice.courses.infrastructure;

import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.infrastructure.elasticsearch.ElasticsearchClient;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.infrastructure.elasticsearch.ElasticsearchRepository;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.backoffice.BackofficeContextInfrastructureTestCase;
import tv.codely.backoffice.courses.domain.BackofficeCourseMother;

final class ElasticsearchBackofficeCourseRepositoryShould extends BackofficeContextInfrastructureTestCase {

    @Autowired
    private ElasticsearchBackofficeCourseRepository repository;

    @BeforeEach
    protected void setUp() throws IOException {
        clearElasticsearch();
    }

    @Test
    void save_a_course() {
        repository(BackofficeCourseMother.random());
    }

    @Test
    void search_all_existing_courses() throws Exception {
        BackofficeCourse course = BackofficeCourseMother.random();
        BackofficeCourse anotherCourse = BackofficeCourseMother.random();

        List<BackofficeCourse> expected = Arrays.asList(course, anotherCourse);

        repository.save(course);
        repository.save(anotherCourse);

        eventually(() -> assertEquals(expected, repository.searchAll()));
    }

    @Test
    void search_courses_using_a_criteria() throws Exception {
        BackofficeCourse matchingCourse = BackofficeCourseMother.create("DDD in Java", "3 days");
        BackofficeCourse anotherMatchingCourse = BackofficeCourseMother.create("DDD in Typescript", "2.5 days");
        BackofficeCourse intellijCourse = BackofficeCourseMother.create("Take advantage of Intellij", "48 hours");
        BackofficeCourse cobolCourse = BackofficeCourseMother.create("Learn Cobol", "5 years");

        Criteria criteria = BackofficeCourseCriteriaMother.nameAndDurationContains("DDD", "days");
        List<BackofficeCourse> expected = Arrays.asList(matchingCourse, anotherMatchingCourse);

        repository.save(matchingCourse);
        repository.save(anotherMatchingCourse);
        repository.save(intellijCourse);
        repository.save(cobolCourse);

        eventually(() -> assertEquals(expected, repository.matching(criteria)));
    }
}
