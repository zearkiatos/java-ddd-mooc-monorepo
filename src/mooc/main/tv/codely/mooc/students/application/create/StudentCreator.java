package tv.codely.mooc.students.application.create;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentEmail;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentName;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.mooc.students.domain.StudentSurname;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
public final class StudentCreator {
    private StudentRepository repository;
    public StudentCreator(StudentRepository repository) {
        this.repository = repository;
    }
    public void create(CreateStudentRequest request) {
        Student student = new Student(
            new StudentId(request.id()),
            new StudentName(request.name()),
            new StudentSurname(request.surname()),
            new StudentEmail(request.email())
        );

        repository.save(student);
    }
}
