package tv.codely.apps.mooc.controller.videos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tv.codely.mooc.videos.application.create.CreateVideoCommand;
import tv.codely.mooc.videos.application.create.CreateVideoRequest;
import tv.codely.mooc.videos.application.create.VideoCreator;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.command.CommandNotRegisteredError;

@RestController
public final class VideosPutController {
    private CommandBus bus;

    public VideosPutController(CommandBus bus) {
        this.bus = bus;
    }

    @PutMapping("/videos/{id}")
    public ResponseEntity<Integer> create(@PathVariable String id, @RequestBody Request request) throws CommandNotRegisteredError {
        bus.dispatch(new CreateVideoCommand(id, request.title(), request.url(), request.description()));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

final class Request {
    private String title;
    private String url;
    private String description;

    String title() {
        return title;
    }

    String url() {
        return url;
    }

    String description() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

