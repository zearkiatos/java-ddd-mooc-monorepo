package tv.codely.mooc.students.infrastructure;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentEmail;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentName;
import tv.codely.mooc.students.domain.StudentSurname;

final class InMemoryStudentRepositoryShould {
    @Test
    void save_a_valid_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();

        Student student = new Student(
                new StudentId("201c4bb3-b790-492c-9985-9919de9ee5c1"),
                new StudentName("Pedro"),
                new StudentSurname("Capriles"),
                new StudentEmail("pedro.capriles@example.com"));

        repository.save(student);

    }

    @Test
    void return_an_existing_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();

        Student student = new Student(
                new StudentId("201c4bb3-b790-492c-9985-9919de9ee5c1"),
                new StudentName("Pedro"),
                new StudentSurname("Capriles"),
                new StudentEmail("pedro.capriles@example.com"));
        repository.save(student);

        Assert.assertEquals(Optional.of(student), repository.search(student.id().toString()));
    }

    @Test
    void not_find_a_non_existing_student() throws Exception {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();

        Assert.assertFalse(repository.search("non-existing-id").isPresent());
    }
}
