package tv.codely.mooc.courses.domain;

import java.util.Objects;
import tv.codely.shared.domain.AggregateRoot;

public final class Course extends AggregateRoot {

    private CourseId id;
    private CourseName name;
    private CourseDuration duration;

    // Default constructor for Hibernate
    public Course() {}

    public Course(CourseId id, CourseName name, CourseDuration duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public CourseId id() {
        return id;
    }

    public CourseName name() {
        return name;
    }

    public CourseDuration duration() {
        return duration;
    }

    public static Course create(CourseId id, CourseName name, CourseDuration duration) {
        Course course = new Course(id, name, duration);

        course.record(new CourseCreatedDomainEvent(id.value(), name.value(), duration.value()));

        return course;
    }

    // Hibernate property accessors for ID conversion
    public String getId() {
        return id != null ? id.value() : null;
    }

    public void setId(String idString) {
        this.id = idString != null ? new CourseId(idString) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        return id.equals(course.id) &&
                name.equals(course.name) &&
                duration.equals(course.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }
}
