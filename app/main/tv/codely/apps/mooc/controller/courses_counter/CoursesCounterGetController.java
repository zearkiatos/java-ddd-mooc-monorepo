package tv.codely.apps.mooc.controller.courses_counter;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public final class CoursesCounterGetController {
    CoursesCounterFinderShould finder;

    public CoursesCounterGetController(CoursesCounterFinderShould finder) {
        this.finder = finder;
    }

    @GetMapping("/courses-counter")
    public HashMap<String, Integer> index() {
        CoursesCounterResponse response = finder.find();
        return new HashMap<String,Integer>() {{
            put("total", response.total());
        }};
    }



}
