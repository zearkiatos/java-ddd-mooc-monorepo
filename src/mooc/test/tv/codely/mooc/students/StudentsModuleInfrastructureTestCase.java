package tv.codely.mooc.students;

import org.springframework.beans.factory.annotation.Autowired;

import tv.codely.mooc.courses.infrastructure.InMemoryCourseRepository;
import tv.codely.shared.infrastructure.InfrastructureTestCase;

public abstract class StudentsModuleInfrastructureTestCase extends InfrastructureTestCase {
    @Autowired
    protected InMemoryCourseRepository repository;
}
