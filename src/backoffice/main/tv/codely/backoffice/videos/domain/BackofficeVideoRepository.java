package tv.codely.backoffice.videos.domain;

import java.util.List;

public interface BackofficeVideoRepository {
    void save(BackofficeVideo video);

    List<BackofficeVideo> searchAll();
}
