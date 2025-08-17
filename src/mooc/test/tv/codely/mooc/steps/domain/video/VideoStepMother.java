package tv.codely.mooc.steps.domain.video;

import tv.codely.shared.domain.VideoUrl;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.StepTitle;
import tv.codely.mooc.steps.domain.video.VideoTextStep;
import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepIdMother;
import tv.codely.mooc.steps.domain.StepTitleMother;


public class VideoStepMother {
    public static VideoStep create(StepId id, StepTitle title,  VideoUrl videoUrl, VideoTextStep text) {
        return new VideoStep(id, title, videoUrl, text);
    }

    public static VideoStep random() {
        return create(StepIdMother.random(), StepTitleMother.random(), VideoUrlStepMother.random(), VideoTextStepMother.random());
    }
}
