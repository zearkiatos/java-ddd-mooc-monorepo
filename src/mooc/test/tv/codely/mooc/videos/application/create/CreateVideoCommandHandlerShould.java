package tv.codely.mooc.videos.application.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tv.codely.mooc.videos.VideosModuleUnitTestCase;
import tv.codely.mooc.videos.application.create.CreateVideoCommand;
import tv.codely.mooc.videos.application.create.CreateVideoCommandHandler;
import tv.codely.mooc.videos.application.create.VideoCreator;
import tv.codely.mooc.videos.domain.Video;
import tv.codely.mooc.videos.domain.VideoCreatedDomainEvent;
import tv.codely.mooc.videos.domain.VideoCreatedDomainEventMother;

final class CreateVideoCommandHandlerShould extends VideosModuleUnitTestCase {
    private CreateVideoCommandHnalder handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new CreateVideoCommandHandler(new VideoCreator(repository, eventBus));
    }

    @Test
    void create_a_valid_video() {
        CreateVideoCommand command = CreateVideoCommandMother.random();

        Video                   video       = VideoMother.fromRequest(command);
        VideoCreatedDomainEvent domainEvent = VideoCreatedDomainEventMother.fromVideo(video);

        handler.handle(command);

        shouldHaveSaved(video);
        shouldHavePublished(domainEvent);
    }
}
