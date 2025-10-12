package tv.codely.mooc.videos.application.create;

import tv.codely.shared.domain.bus.command.Command;

public final class CreateVideoCommand implements Command {
    private String id;
    private String title;
    private String url;
    private String description;

    public CreateVideoCommand(String id, String title, String url, String description) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String url() {
        return url;
    }

    public String description() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateVideoCommand)) return false;

        CreateVideoCommand video = (CreateVideoCommand) o;

        return id.equals(video.id) &&
               title.equals(video.title) &&
               url.equals(video.url) &&
               description.equals(video.description);
    }
}
