package tv.codely.mooc.steps.infrastructure;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import javax.transaction.Transactional;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import tv.codely.mooc.steps.StepsModuleInfrastructureTestCase;
import tv.codely.mooc.steps.domain.video.VideoStep;
import tv.codely.mooc.steps.domain.video.VideoStepMother;
import tv.codely.mooc.steps.domain.challenge.ChallengeStep;
import tv.codely.mooc.steps.domain.challenge.ChallengeStepMother;

@Transactional
@ActiveProfiles("local")
public class MySqlStepRepositoryShould extends StepsModuleInfrastructureTestCase {
    @Test
    void save_a_video_step() {
        VideoStep step = VideoStepMother.random();

        mySqlStepRepository.save(step);
    }

    @Test
    void save_a_challenge_step() {
        ChallengeStep step = ChallengeStepMother.random();

        mySqlStepRepository.save(step);
    }

    @Test
    void return_an_exsisting_challenge_step() {
        ChallengeStep step = ChallengeStepMother.random();

        mySqlStepRepository.save(step);

        assertEquals(step, mySqlStepRepository.search(step.id()).get());
    }

    @Test
    void return_an_existing_video_step() {
        VideoStep step = VideoStepMother.random();

        mySqlStepRepository.save(step);

        assertEquals(step, mySqlStepRepository.search(step.id()).get());
    }

    @Test
    void not_return_a_non_existing_step() {
        assertFalse(mySqlStepRepository.search(ChallengeStepMother.random().id()).isPresent());
        assertFalse(mySqlStepRepository.search(VideoStepMother.random().id()).isPresent());
    }



}
