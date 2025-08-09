package tv.codely.mooc.students;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentRepository;

public abstract class StudentsModuleUnitTestCase {
    protected StudentRepository repository;

    @BeforeEach
    protected void setUp() {
        repository = mock(StudentRepository.class);
    }

    protected void shouldHaveSaved(Student student) {
        verify(repository, atLeastOnce()).save(student);
    }
}
