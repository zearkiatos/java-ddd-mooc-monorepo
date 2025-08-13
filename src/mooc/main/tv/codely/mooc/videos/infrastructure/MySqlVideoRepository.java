package tv.codely.mooc.videos.infrastructure;

import java.util.Optional;
import org.hibernate.SessionFactory;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Profile;

import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoRepository;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
@Profile("local")
public class MySqlVideoRepository implements VideoRepository {
    private SessionFactory sessionFactory;

    public MySqlVideoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public void save(Video video) {
        sessionFactory.getCurrentSession().save(video);
    }

    @Override
    public Optional<Video> search(VideoId id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(Video.class, id.value()));
    }
}
