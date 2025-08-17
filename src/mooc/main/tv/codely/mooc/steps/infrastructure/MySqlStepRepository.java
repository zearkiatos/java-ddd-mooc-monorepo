package tv.codely.mooc.steps.infrastructure;

import java.util.Optional;
import org.hibernate.SessionFactory;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Profile;

import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;
import tv.codely.mooc.steps.domain.StepRepository;

@ServiceInjectable
@Profile("local")
public class MySqlStepRepository extends HibernateRepository<Step> implements StepRepository {
    private SessionFactory sessionFactory;

    public MySqlStepRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Step.class);
    }

    @Override
    @Transactional
    public void save(Step step) {
        persist(step);
    }

    @Override
    public Optional<Step> search(StepId id) {
        return byId(id);
    }
}
