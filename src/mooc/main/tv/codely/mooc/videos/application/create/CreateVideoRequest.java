package tv.codely.mooc.videos.application.create;

public final class CreateVideoRequest {
    private String id;
    private String title;
    private String description;
    private String url;

    public CreateVideoRequest(String id, String title, String description, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateVideoRequest)) return false;

        CreateVideoRequest video = (CreateVideoRequest) o;

        return id.equals(video.id) &&
               title.equals(video.title) &&
               description.equals(video.description) &&
               url.equals(video.url);
    }
}
