package tv.codely.mooc.students.application.create;

import tv.codely.mooc.students.application.create.CreateStudentCommand;
import tv.codely.mooc.students.domain.StudentEmail;
import tv.codely.mooc.students.domain.StudentEmailMother;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentIdMother;
import tv.codely.mooc.students.domain.StudentName;
import tv.codely.mooc.students.domain.StudentNameMother;
import tv.codely.mooc.students.domain.StudentSurname;
import tv.codely.mooc.students.domain.StudentSurnameMother;

public final class CreateStudentCommandMother {
    public static CreateStudentCommand create(StudentId id, StudentName name, StudentSurname surname, StudentEmail email) {
        return new CreateStudentCommand(id.value(), name.value(), surname.value(), email.value());
    }

    public static CreateStudentCommand random() {
        return create(StudentIdMother.random(), StudentNameMother.random(), StudentSurnameMother.random(), StudentEmailMother.random());
    }
}
