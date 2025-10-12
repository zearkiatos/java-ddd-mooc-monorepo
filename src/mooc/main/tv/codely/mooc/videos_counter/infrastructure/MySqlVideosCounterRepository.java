package tv.codely.mooc.videos_counter.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;
import tv.codely.mooc.videos_counter.domain.VideosCounter;
import tv.codely.mooc.videos_counter.domain.VideosCounterId;
import tv.codely.mooc.videos_counter.domain.VideosCounterRepository;


@ServiceInjectable
@Profile("local")
public class MySqlVideosCounterRepository extends HibernateRepository<VideosCounter> implements VideosCounterRepository {
    public MySqlVideosCounterRepository(SessionFactory sessionFactory) {
        super(sessionFactory, VideosCounter.class);
    }

    @Override
    public void save(VideosCounter counter) {
        persist(counter);
    }

    @Override
    public Optional<VideosCounter> search() {

        CriteriaBuilder builder  = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<VideosCounter> criteria = builder.createQuery(aggregateClass);
        criteria.from(aggregateClass);
        List<VideosCounter> videosCounter = sessionFactory.getCurrentSession().createQuery(criteria).getResultList();

        return 0 == videosCounter.size() ? Optional.empty() : Optional.ofNullable(videosCounter.get(0));

//        return byId(new CoursesCounterId("2a4743fc-12a1-4563-9bb7-b02f398e5a88"));
//        List<CoursesCounter> coursesCounter = sessionFactory.getCurrentSession().createQuery("FROM courses_counter").list();

//        return 0 == coursesCounter.size() ? Optional.empty() : Optional.ofNullable(coursesCounter.get(0));
    }

}
