package tv.codely.backoffice.courses.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.criteria.Criteria;

public final class InMemoryCacheBackofficeCourseRepository implements BackofficeCourseRepository {
    private final BackofficeCourseRepository repository;
    private List<BackofficeCourse> courses = new ArrayList<>();
    private HashMap<String, List<BackofficeCourse>> matchingCourses = new HashMap<>();

    public InMemoryCacheBackofficeCourseRepository(BackofficeCourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(BackofficeCourse course) {
        repository.save(course);
    }

    @Override
    public List<BackofficeCourse> searchAll() {
        return courses.isEmpty() ? searchAndFillCache() : courses;
    }

    @Override
    public List<BackofficeCourse> matching(Criteria criteria) {
        return matchingCourses.containsKey(criteria.serialize()) ? matchingCourses.get(criteria.serialize()) : searchMatchingAndFillCache(criteria);
    }

    private List<BackofficeCourse> searchMatchingAndFillCache(Criteria criteria) {
        List<BackofficeCourse> courses = repository.matching(criteria);

        this.matchingCourses.put(criteria.serialize(), courses);

        return courses;
    }

     private List<BackofficeCourse> searchAndFillCache() {
        List<BackofficeCourse> courses = repository.searchAll();

        this.courses = courses;

        return courses;
    }
}
