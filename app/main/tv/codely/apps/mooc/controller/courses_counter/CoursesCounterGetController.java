package tv.codely.apps.mooc.controller.courses_counter;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import tv.codely.mooc.courses_counter.application.find.CoursesCounterResponse;
import tv.codely.mooc.courses_counter.application.find.FindCoursesCounterQuery;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;

import java.util.HashMap;


@RestController
public final class CoursesCounterGetController {
    private QueryBus bus;

    public CoursesCounterGetController(QueryBus bus) {
        this.bus = bus;
    }

    @GetMapping("/courses-counter")
    public HashMap<String, Integer> index() throws QueryNotRegisteredError {
        CoursesCounterResponse response = bus.ask(new FindCoursesCounterQuery());

        return new HashMap<String,Integer>() {{
            put("total", response.total());
        }};
    }



}
