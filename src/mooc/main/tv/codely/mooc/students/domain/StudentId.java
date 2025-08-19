package tv.codely.mooc.students.domain;

import tv.codely.shared.domain.Identifier;

public final class StudentId extends Identifier {
    public StudentId(String value) {
        super(value);
    }

    public StudentId() {
        super();
    }

     public String getValue() {
        return value();
    }
}
