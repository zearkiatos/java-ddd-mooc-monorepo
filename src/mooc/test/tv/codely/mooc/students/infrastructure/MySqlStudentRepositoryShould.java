package src.mooc.test.tv.codely.mooc.students.infrastructure;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import tv.codely.mooc.students.StudentsModuleInfrastructureTestCase;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentMother;
import tv.codely.mooc.students.domain.StudentIdMother;

@Transactional
@ActiveProfiles("local")
final class MySqlStudentRepositoryShould extends StudentsModuleInfrastructureTestCase {
    @Test
    void save_a_valid_student() {
        Student student = StudentMother.random();

        mySqlStudentRepository.save(student);

    }

    @Test
    void search_an_existing_student() {
        Student student = StudentMother.random();

        mySqlStudentRepository.save(student);

        assertEquals(Optional.of(student), mySqlStudentRepository.search(student.id()));
    }

    @Test
    void not_find_a_non_existing_student() throws Exception {
        assertFalse(mySqlStudentRepository.search(StudentIdMother.random()).isPresent());
    }
}
