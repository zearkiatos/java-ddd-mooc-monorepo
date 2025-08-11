package tv.codely.mooc.courses.infrastructure;

import java.util.Optional;
import org.hibernate.SessionFactory;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Profile;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
@Profile("local")
public class MySqlCourseRepository implements CourseRepository {
    private SessionFactory sessionFactory;

    public MySqlCourseRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public void save(Course course) {
        sessionFactory.getCurrentSession().save(course);
    }

    @Override
    public Optional<Course> search(CourseId id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(Course.class, id));
    }

}
