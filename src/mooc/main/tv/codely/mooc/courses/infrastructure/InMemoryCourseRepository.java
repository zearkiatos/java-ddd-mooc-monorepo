package tv.codely.mooc.courses.infrastructure;

import java.util.HashMap;
import java.util.Optional;


import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
public final class InMemoryCourseRepository implements CourseRepository {
    private HashMap<String, Course> courses = new HashMap<>();

    @Override
    public void save(Course course) {
        courses.put(course.id().toString(), course);
    }

    @Override
    public Optional<Course> search(String id) {
        return Optional.ofNullable(courses.get(id));
    }

}
