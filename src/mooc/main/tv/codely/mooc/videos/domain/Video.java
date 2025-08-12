package tv.codely.mooc.videos.domain;

public final class Video {
    private VideoId id;
    private VideoTitle title;
    private VideoDescription description;
    private VideoUrl url;

    public Video() {}

    public Video(VideoId id, VideoTitle title, VideoDescription description, VideoUrl url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public VideoId id() {
        return id;
    }

    public VideoTitle title() {
        return title;
    }

    public VideoDescription description() {
        return description;
    }

    public VideoUrl url() {
        return url;
    }
}
