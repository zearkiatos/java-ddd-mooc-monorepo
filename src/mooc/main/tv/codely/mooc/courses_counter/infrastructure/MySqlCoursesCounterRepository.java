package tv.codely.mooc.courses_counter.infrastructure;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


@ServiceInjectable
@Transactional("mooc-transaction_manager")
public class MySqlCoursesCounterRepository extends HibernateRepository<CoursesCounter> implements CoursesCounterRepository {
    public MySqlCoursesCounterRepository(@Qualifier("mooc-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, CoursesCounter.class);
    }

    @Override
    public void save(CoursesCounter counter) {
        persist(counter);
    }

    @Override
    public Optional<CoursesCounter> search() {

        CriteriaBuilder               builder  = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<CoursesCounter> criteria = builder.createQuery(aggregateClass);
        criteria.from(aggregateClass);
        List<CoursesCounter> coursesCounter = sessionFactory.getCurrentSession().createQuery(criteria).getResultList();

        return 0 == coursesCounter.size() ? Optional.empty() : Optional.ofNullable(coursesCounter.get(0));

//        return byId(new CoursesCounterId("2a4743fc-12a1-4563-9bb7-b02f398e5a88"));
//        List<CoursesCounter> coursesCounter = sessionFactory.getCurrentSession().createQuery("FROM courses_counter").list();

//        return 0 == coursesCounter.size() ? Optional.empty() : Optional.ofNullable(coursesCounter.get(0));
    }
}
