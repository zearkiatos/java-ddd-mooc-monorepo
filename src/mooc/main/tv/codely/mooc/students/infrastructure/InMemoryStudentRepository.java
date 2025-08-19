package tv.codely.mooc.students.infrastructure;

import org.springframework.context.annotation.Profile;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.domain.ServiceInjectable;


import java.util.HashMap;
import java.util.Optional;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;

@ServiceInjectable
@Profile({"test"})
public final class InMemoryStudentRepository implements StudentRepository {

    private HashMap<String, Student> students = new HashMap<>();
    @Override
    public void save(Student student) {
        students.put(student.id().toString(), student);
    }

    @Override
    public Optional<Student> search(StudentId id) {
        return Optional.ofNullable(students.get(id.toString()));
    }
}
