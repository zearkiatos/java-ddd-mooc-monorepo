package tv.codely.mooc.students.application.create;

import org.springframework.transaction.annotation.Transactional;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentEmail;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentName;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.mooc.students.domain.StudentSurname;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.bus.event.EventBus;

@ServiceInjectable
public class StudentCreator {
    private StudentRepository repository;
    private final EventBus eventBus;
    public StudentCreator(StudentRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }
    @Transactional
    public void create(StudentId id, StudentName name, StudentSurname surname, StudentEmail email) {
        Student student = Student.create(id, name, surname, email);
        repository.save(student);
        // eventBus.publish(student.pullDomainEvents());
    }
}
