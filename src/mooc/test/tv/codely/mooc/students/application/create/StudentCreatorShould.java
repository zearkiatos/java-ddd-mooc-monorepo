package tv.codely.mooc.students.application.create;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentRepository;

final class StudentCreatorShould {
    @Test
    void save_a_valid_student() throws Exception {
        StudentRepository repository = mock(StudentRepository.class);
        StudentCreator creator = new StudentCreator(repository);

        Student student = new Student("201c4bb3-b790-492c-9985-9919de9ee5c1", "Pedro", "Capriles", "pedro.capriles@example.com");

        creator.create(student.id(), student.name(), student.surname(), student.email());

        verify(repository, atLeastOnce()).save(student);
    }
}
