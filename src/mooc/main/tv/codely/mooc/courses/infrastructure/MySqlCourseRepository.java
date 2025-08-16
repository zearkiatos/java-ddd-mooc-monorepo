package tv.codely.mooc.courses.infrastructure;

import java.util.Optional;
import org.hibernate.SessionFactory;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Profile;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;

@ServiceInjectable
@Profile("local")
public class MySqlCourseRepository extends HibernateRepository<Course> implements CourseRepository {
    private SessionFactory sessionFactory;

    public MySqlCourseRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Course.class);
    }
    @Override
    public void save(Course course) {
         persist(course);
    }

    @Override
    public Optional<Course> search(CourseId id) {
        return byId(id);
    }

}
