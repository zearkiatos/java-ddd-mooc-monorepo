package tv.codely.mooc.students.infrastructure;

import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.domain.ServiceInjectable;

import java.util.HashMap;
import java.util.Optional;

import tv.codely.mooc.students.domain.Student;

@ServiceInjectable
public class InMemoryStudentRepository implements StudentRepository {

    private HashMap<String, Student> students = new HashMap<>();
    @Override
    public void save(Student student) {
        students.put(student.id(), student);
    }

    @Override
    public Optional<Student> search(String id) {
        return Optional.ofNullable(students.get(id));
    }
}
