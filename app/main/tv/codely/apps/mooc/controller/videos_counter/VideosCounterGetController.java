package tv.codely.apps.mooc.controller.videos_counter;

import org.springframework.web.bind.annotation.RestController;

import tv.codely.mooc.videos_counter.application.find.FindVideosCounterQuery;
import tv.codely.mooc.videos_counter.application.find.VideosCounterResponse;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;
import tv.codely.shared.domain.bus.query.QueryBus;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public final class VideosCounterGetController {
    private QueryBus bus;

    public VideosCounterGetController(QueryBus bus) {
        this.bus = bus;
    }

    @GetMapping("/videos-counter")
    public HashMap<String, Integer> index() throws QueryNotRegisteredError {
        VideosCounterResponse response = bus.ask(new FindVideosCounterQuery());

        return new HashMap<String,Integer>() {{
            put("total", response.total());
        }};
    }
}
