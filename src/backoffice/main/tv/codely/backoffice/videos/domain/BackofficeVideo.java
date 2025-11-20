package tv.codely.backoffice.videos.domain;

public final class BackofficeVideo {
    private final String id;
    private final String title;
    private final String description;
    private final String url;

    public BackofficeVideo() {
        id          = null;
        title       = null;
        description = null;
        url         = null;
    }

    public BackofficeVideo(String id, String title, String description, String url) {
        this.id          = id;
        this.title       = title;
        this.description = description;
        this.url         = url;
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
