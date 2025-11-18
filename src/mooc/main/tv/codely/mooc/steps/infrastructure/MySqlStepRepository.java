package tv.codely.mooc.steps.infrastructure;

import java.util.Optional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;
import tv.codely.mooc.steps.domain.StepRepository;

@ServiceInjectable
@Transactional("mooc-transaction_manager")
public class MySqlStepRepository extends HibernateRepository<Step> implements StepRepository {

    public MySqlStepRepository(@Qualifier("mooc-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Step.class);
    }

    @Override
    @Transactional
    public void save(Step step) {
        persist(step);
    }

    @Override
    public Optional<Step> search(StepId id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Step.class, id));
    }
}
