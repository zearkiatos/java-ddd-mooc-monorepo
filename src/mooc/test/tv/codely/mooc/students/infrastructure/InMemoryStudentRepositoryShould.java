package tv.codely.mooc.students.infrastructure;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import tv.codely.mooc.students.StudentsModuleInfrastructureTestCase;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentIdMother;
import tv.codely.mooc.students.domain.StudentMother;

@ActiveProfiles("test")
final class InMemoryStudentRepositoryShould extends StudentsModuleInfrastructureTestCase {
    @Test
    void save_a_valid_student() {
        Student student = StudentMother.random();

        inMemoryStudentRepository.save(student);
    }

    @Test
    void return_an_existing_student() {
        Student student = StudentMother.random();
        inMemoryStudentRepository.save(student);

        Assert.assertEquals(Optional.of(student), inMemoryStudentRepository.search(student.id()));
    }

    @Test
    void not_find_a_non_existing_student() throws Exception {
        Assert.assertFalse(inMemoryStudentRepository.search(StudentIdMother.random()).isPresent());
    }
}
