package tv.codely.mooc.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.mooc.steps.domain.StepRepository;


import tv.codely.apps.mooc.backend.MoocBackendApplication;
import tv.codely.shared.infrastructure.InfrastructureTestCase;
import tv.codely.mooc.MoocContextInfrastructureTestCase;

@ContextConfiguration(classes=BackofficeBackendApplication.class)
@SpringBootTest
public abstract class StepsModuleInfrastructureTestCase extends MoocContextInfrastructureTestCase {

    @Autowired
    protected StepRepository mySqlStepRepository;
}
