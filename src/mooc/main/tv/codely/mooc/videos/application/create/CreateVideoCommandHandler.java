package tv.codely.mooc.videos.application.create;

import tv.codely.shared.domain.bus.command.CommandHandler;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.mooc.videos.domain.VideoDescription;
import tv.codely.mooc.videos.domain.VideoId;
import tv.codely.mooc.videos.domain.VideoTitle;
import tv.codely.shared.domain.VideoUrl;

@ServiceInjectable
public class CreateVideoCommandHandler implements CommandHandler<CreateVideoCommand> {

    private VideoCreator creator;

    public CreateVideoCommandHandler(VideoCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateVideoCommand command) {
        VideoId id = new VideoId(command.id());
        VideoTitle title = new VideoTitle(command.title());
        VideoDescription description = new VideoDescription(command.description());
        VideoUrl url = new VideoUrl(command.url());
        creator.create(id, title, description, url);
    }
}
