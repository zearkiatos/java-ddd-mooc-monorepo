package tv.codely.mooc.students.domain;

import tv.codely.shared.domain.StringValueObject;

public final class StudentSurname extends StringValueObject {

    public StudentSurname(String value) {
        super(value);
    }

    // Default constructor for Hibernate
    protected StudentSurname() {
        super();
    }

}
