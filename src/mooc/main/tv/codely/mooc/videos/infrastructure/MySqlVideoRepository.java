package tv.codely.mooc.videos.infrastructure;

import java.util.Optional;
import org.hibernate.SessionFactory;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Profile;

import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoRepository;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;

@ServiceInjectable
@Profile("local")
public class MySqlVideoRepository extends HibernateRepository<Video> implements VideoRepository {
    private SessionFactory sessionFactory;

    public MySqlVideoRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Video.class);
    }
    @Override
    public void save(Video video) {
        persist(video);
    }

    @Override
    public Optional<Video> search(VideoId id) {
        return byId(id);
    }
}
