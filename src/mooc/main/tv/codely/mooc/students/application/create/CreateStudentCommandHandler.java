package tv.codely.mooc.students.application.create;

import tv.codely.mooc.students.domain.StudentEmail;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentName;
import tv.codely.mooc.students.domain.StudentSurname;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.bus.command.CommandHandler;

@ServiceInjectable
public final class CreateStudentCommandHandler implements CommandHandler<CreateStudentCommand> {
    private StudentCreator creator;

    public CreateStudentCommandHandler(StudentCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateStudentCommand command) {
        StudentId id = new StudentId(command.id());
        StudentName name = new StudentName(command.name());
        StudentSurname surname = new StudentSurname(command.surname());
        StudentEmail email = new StudentEmail(command.email());

        creator.create(id, name, surname, email);
    }

}
