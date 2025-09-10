package tv.codely.mooc.students.application.create;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentEmail;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentName;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.mooc.students.domain.StudentSurname;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.bus.event.EventBus;

@ServiceInjectable
public final class StudentCreator {
    private StudentRepository repository;
    private final EventBus eventBus;
    public StudentCreator(StudentRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }
    public void create(CreateStudentRequest request) {
        StudentId id = new StudentId(request.id());
        StudentName name = new StudentName(request.name());
        StudentSurname surname = new StudentSurname(request.surname());
        StudentEmail email = new StudentEmail(request.email());

        Student student = Student.create(id, name, surname, email);

        repository.save(student);
        // eventBus.publish(student.pullDomainEvents());
    }
}
