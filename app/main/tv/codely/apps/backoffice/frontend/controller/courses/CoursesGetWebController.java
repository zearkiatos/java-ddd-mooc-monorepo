package tv.codely.apps.backoffice.frontend.controller.courses;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import tv.codely.mooc.courses_counter.application.find.CoursesCounterResponse;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;
import tv.codely.mooc.courses_counter.application.find.FindCoursesCounterQuery;
import tv.codely.shared.domain.bus.query.QueryBus;

@Controller
public final class CoursesGetWebController {
    private final QueryBus bus;

    public CoursesGetWebController(QueryBus bus) {
        this.bus = bus;
    }

    @GetMapping("/courses")
    public ModelAndView index() throws QueryNotRegisteredError  {
        CoursesCounterResponse counterResponse = bus.ask(new FindCoursesCounterQuery());

        return new ModelAndView("pages/courses", new HashMap<String, Serializable>() {{
            put("title", "Courses");
            put("description", "Courses DinoGeek - Backoffice");
            put("courses_counter", counterResponse.total());
        }});
    }
}
