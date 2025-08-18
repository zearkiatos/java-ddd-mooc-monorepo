package tv.codely.mooc.steps.domain.video;

import tv.codely.shared.domain.VideoUrl;
import tv.codely.mooc.steps.domain.*;


public final class VideoStep extends Step {
    private VideoUrl videoUrl;
    private VideoTextStep text;

    public VideoStep(StepId id, StepTitle title, VideoUrl videoUrl, VideoTextStep text) {
        super(id, title);
        this.text = text;
        this.videoUrl = videoUrl;
    }

    // Default constructor for Hibernate
    public VideoStep() {
        super();
        this.videoUrl = null;
        this.text = new VideoTextStep();
    }

    public VideoUrl videoUrl() {
        return videoUrl;
    }

    public VideoTextStep text() {
        return text;
    }

    // Hibernate getters/setters
    public VideoUrl getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(VideoUrl videoUrl) {
        this.videoUrl = videoUrl;
    }

    public VideoTextStep getText() {
        return text;
    }

    public void setText(VideoTextStep text) {
        this.text = text;
    }
}
