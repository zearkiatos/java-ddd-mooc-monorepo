package tv.codely.mooc.students.application.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tv.codely.mooc.students.StudentsModuleUnitTestCase;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentMother;

final class CreateStudentCommandHandlerShould extends StudentsModuleUnitTestCase {
    private CreateStudentCommandHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new CreateStudentCommandHandler(new StudentCreator(repository, eventBus));
    }

    @Test
    void create_a_valid_student() {
        CreateStudentCommand command = CreateStudentCommandMother.random();

        Student student = StudentMother.fromRequest(command);

        handler.handle(command);

        shouldHaveSaved(student);
    }
}
