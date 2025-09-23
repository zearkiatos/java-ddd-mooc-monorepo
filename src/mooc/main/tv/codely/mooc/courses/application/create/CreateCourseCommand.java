package tv.codely.mooc.courses.application.create;

import tv.codely.shared.domain.bus.command.Command;

public final class CreateCourseCommand implements Command {
    private String id;
    private String name;
    private String duration;

    public CreateCourseCommand(String id, String name, String duration) {
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
        if (!(o instanceof CreateCourseCommand)) return false;

        CreateCourseCommand course = (CreateCourseCommand) o;

        return id.equals(course.id) &&
               name.equals(course.name) &&
               duration.equals(course.duration);
    }


}
