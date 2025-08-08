package tv.codely.mooc.students.application.create;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentEmail;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentName;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.mooc.students.domain.StudentSurname;

final class StudentCreatorShould {
    @Test
    void save_a_valid_student() throws Exception {
        StudentRepository repository = mock(StudentRepository.class);
        StudentCreator creator = new StudentCreator(repository);

        CreateStudentRequest request = new CreateStudentRequest("201c4bb3-b790-492c-9985-9919de9ee5c1", "Pedro", "Capriles", "pedro.capriles@example.com");

        Student student = new Student(
            new StudentId(request.id()),
            new StudentName(request.name()),
            new StudentSurname(request.surname()),
            new StudentEmail(request.email())
        );
        creator.create(request);

        verify(repository, atLeastOnce()).save(student);
    }
}
