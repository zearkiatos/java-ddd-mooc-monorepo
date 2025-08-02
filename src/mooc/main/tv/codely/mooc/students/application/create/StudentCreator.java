package tv.codely.mooc.students.application.create;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
public final class StudentCreator {
    private StudentRepository repository;
    public StudentCreator(StudentRepository repository) {
        this.repository = repository;
    }
    public void create(String id, String name, String surname, String email) {
        Student student = new Student(id, name, surname, email);

        repository.save(student);
    }
}
