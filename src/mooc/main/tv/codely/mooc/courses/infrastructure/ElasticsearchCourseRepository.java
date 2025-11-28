package tv.codely.mooc.courses.infrastructure;

import java.util.List;

import org.springframework.context.annotation.Primary;

import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.infrastructure.elasticsearch.ElasticsearchClient;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.infrastructure.elasticsearch.ElasticsearchRepository;

@Primary
@ServiceInjectable
public final class ElasticsearchCourseRepository extends ElasticsearchRepository<Course> implements CourseRepository {
    public ElasticsearchCourseRepository(ElasticsearchClient client) {
        super(client);
    }

    @Override
    public void save(Course course) {
        persist(course.id(), course.toPrimitives());
    }

    @Override
    public List<Course> searchAll() {
        return searchAllInElastic(Course::fromPrimitives);
    }

    @Override
    public List<Course> matching(Criteria criteria) {
        return searchByCriteria(criteria, Course::fromPrimitives);
    }

    @Override
    protected String moduleName() {
        return "courses";
    }
}
 