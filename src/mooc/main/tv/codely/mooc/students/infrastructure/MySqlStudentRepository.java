package tv.codely.mooc.students.infrastructure;

import java.util.Optional;
import org.hibernate.SessionFactory;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Profile;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;

@ServiceInjectable
@Profile("local")
public class MySqlStudentRepository extends HibernateRepository<Student> implements StudentRepository  {

    public MySqlStudentRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Student.class);
    }
    @Override
    public void save(Student student) {
         persist(student);
    }

    @Override
    public Optional<Student> search(StudentId id) {
        return byId(id);
    }
}
