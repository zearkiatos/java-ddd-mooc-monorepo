package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.Identifier;

public final class CourseId extends Identifier {

    public CourseId(String value) {
        super(value);
    }

    // Default constructor for Hibernate
    public CourseId() {
        super();
    }

     public String getValue() {
        return value();
    }

}
