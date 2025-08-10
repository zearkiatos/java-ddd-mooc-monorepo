package tv.codely.mooc.students.infrastructure;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import tv.codely.mooc.students.StudentsModuleInfrastructureTestCase;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentIdMother;
import tv.codely.mooc.students.domain.StudentMother;

final class InMemoryStudentRepositoryShould extends StudentsModuleInfrastructureTestCase {
    @Test
    void save_a_valid_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();

        Student student = StudentMother.random();

        repository.save(student);
    }

    @Test
    void return_an_existing_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();

        Student student = StudentMother.random();
        repository.save(student);

        Assert.assertEquals(Optional.of(student), repository.search(student.id()));
    }

    @Test
    void not_find_a_non_existing_student() throws Exception {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();

        Assert.assertFalse(repository.search(StudentIdMother.random()).isPresent());
    }
}
