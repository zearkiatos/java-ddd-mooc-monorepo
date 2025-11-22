package tv.codely.backoffice.videos.application;

import tv.codely.shared.domain.bus.query.Response;
import tv.codely.backoffice.videos.domain.BackofficeVideo;

public class BackofficeVideoResponse implements Response {
    private final String id;
    private final String title;
    private final String description;
    private final String url;

    public BackofficeVideoResponse(String id, String title, String description,String url) {
        this.id       = id;
        this.title     = title;
        this.description = description;
        this.url = url;
    }

    public static BackofficeVideoResponse fromAggregate(BackofficeVideo video) {
        return new BackofficeVideoResponse(video.id(), video.title(), video.description(), video.url());
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public String url() {
        return url;
    }
}
