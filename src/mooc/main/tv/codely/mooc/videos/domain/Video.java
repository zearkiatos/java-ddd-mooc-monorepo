package tv.codely.mooc.videos.domain;

import java.util.Objects;

import tv.codely.shared.domain.AggregateRoot;
import tv.codely.shared.domain.VideoUrl;

public final class Video extends AggregateRoot {
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

    public static Video create(VideoId id, VideoTitle title, VideoDescription description, VideoUrl url) {
        Video video = new Video(id, title, description, url);

        video.record(new VideoCreatedDomainEvent(
            id.value(),
            title.value(),
            description.value(),
            url.value()
        ));

        return video;
    }

    // Hibernate property accessors for ID conversion
    public String getId() {
        return id != null ? id.value() : null;
    }

    public void setId(String idString) {
        this.id = idString != null ? new VideoId(idString) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (!id.equals(video.id)) return false;
        if (!title.equals(video.title)) return false;
        if (!description.equals(video.description)) return false;
        return url.equals(video.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, url);
    }
}
