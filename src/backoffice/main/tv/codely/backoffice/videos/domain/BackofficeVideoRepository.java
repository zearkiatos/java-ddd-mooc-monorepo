package tv.codely.backoffice.videos.domain;

import java.util.List;

import tv.codely.shared.domain.criteria.Criteria;

public interface BackofficeVideoRepository {
    void save(BackofficeVideo video);

    List<BackofficeVideo> searchAll();

     List<BackofficeVideo> matching(Criteria criteria);
}
