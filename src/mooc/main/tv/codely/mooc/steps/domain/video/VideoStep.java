package tv.codely.mooc.steps.domain.video;

import tv.codely.shared.domain.VideoUrl;
import tv.codely.mooc.steps.domain.*;


public final class VideoStep extends Step {
    private final VideoUrl videoUrl;
    private final VideoTextStep text;

    public VideoStep(StepId id, StepTitle title, VideoUrl videoUrl, VideoTextStep text) {
        super(id, title);
        this.text = text;
        this.videoUrl = videoUrl;
    }

    public VideoUrl videoUrl() {
        return videoUrl;
    }

    public VideoTextStep text() {
        return text;
    }

    public VideoStep() {
        super(null, null);
        this.videoUrl = null;
        this.text = new VideoTextStep();
    }
}
