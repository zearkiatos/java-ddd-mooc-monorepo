package tv.codely.mooc.courses.application.create;

public final class CreateCourseRequest {
    private String id;
    private String name;
    private String duration;

    public CreateCourseRequest(String id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String duration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateCourseRequest)) return false;

        CreateCourseRequest course = (CreateCourseRequest) o;

        return id.equals(course.id) &&
               name.equals(course.name) &&
               duration.equals(course.duration);
    }


}
