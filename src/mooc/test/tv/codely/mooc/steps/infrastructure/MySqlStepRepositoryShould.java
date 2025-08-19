package tv.codely.mooc.steps.infrastructure;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import javax.transaction.Transactional;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import tv.codely.mooc.steps.StepsModuleInfrastructureTestCase;
import tv.codely.mooc.steps.domain.video.VideoStep;
import tv.codely.mooc.steps.domain.video.VideoStepMother;
import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.challenge.ChallengeStep;
import tv.codely.mooc.steps.domain.challenge.ChallengeStepMother;

@Transactional
@ActiveProfiles("local")
public class MySqlStepRepositoryShould extends StepsModuleInfrastructureTestCase {
    @Test
    void save_a_step() {
        for (Step step : steps()) {
            mySqlStepRepository.save(step);
        }
        VideoStep step = VideoStepMother.random();
    }

    @Test
    void return_an_exsisting_step() {
        for (Step step : steps()) {
            mySqlStepRepository.save(step);

            assertEquals(step, mySqlStepRepository.search(step.id()).get());
        }
    }

    @Test
    void not_return_a_non_existing_step() {
        for (Step step : steps()) {
            assertFalse(mySqlStepRepository.search(step.id()).isPresent());
        }
    }

    private List<? extends Step> steps() {
        return Arrays.asList(
                VideoStepMother.random(),
                ChallengeStepMother.random());
    }

}
