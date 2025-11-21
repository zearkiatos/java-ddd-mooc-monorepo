package tv.codely.backoffice.courses.domain;

import java.util.List;

import tv.codely.shared.domain.criteria.Criteria;

public interface BackofficeCourseRepository {
    void save(BackofficeCourse course);

    List<BackofficeCourse> searchAll();

    List<BackofficeCourse> matching(Criteria criteria);
}
