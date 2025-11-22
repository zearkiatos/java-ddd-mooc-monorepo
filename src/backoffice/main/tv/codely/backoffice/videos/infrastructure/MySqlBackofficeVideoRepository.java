package tv.codely.backoffice.videos.infrastructure;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import tv.codely.shared.domain.criteria.Criteria;

import tv.codely.backoffice.videos.domain.BackofficeVideo;
import tv.codely.backoffice.videos.domain.BackofficeVideoRepository;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
@Transactional("backoffice-transaction_manager")
public class MySqlBackofficeVideoRepository extends HibernateRepository<BackofficeVideo> implements BackofficeVideoRepository {
    public MySqlBackofficeVideoRepository(@Qualifier("backoffice-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, BackofficeVideo.class);
    }

    @Override
    public void save(BackofficeVideo video) {
        persist(video);
    }

    @Override
    public List<BackofficeVideo> searchAll() {
        return all();
    }

    @Override
    public List<BackofficeVideo> matching(Criteria criteria) {
        return byCriteria(criteria);
    };
}
