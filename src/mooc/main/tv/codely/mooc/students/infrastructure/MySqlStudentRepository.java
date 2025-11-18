package tv.codely.mooc.students.infrastructure;

import java.util.Optional;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;

@ServiceInjectable
@Transactional("mooc-transaction_manager")
public class MySqlStudentRepository extends HibernateRepository<Student> implements StudentRepository  {

    public MySqlStudentRepository(@Qualifier("mooc-session_factory") SessionFactory sessionFactory) {
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
