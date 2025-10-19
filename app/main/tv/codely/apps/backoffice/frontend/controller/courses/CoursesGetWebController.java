package tv.codely.apps.backoffice.frontend.controller.courses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView index(@ModelAttribute("inputs") HashMap<String, Serializable> inputs,
    @ModelAttribute("errors") HashMap<String, List<String>> errors) throws QueryNotRegisteredError  {
        CoursesCounterResponse counterResponse = bus.ask(new FindCoursesCounterQuery());

        return new ModelAndView("pages/courses/courses", new HashMap<String, Serializable>() {{
            put("title", "Courses");
            put("description", "Courses DinoGeek - Backoffice");
            put("courses_counter", counterResponse.total());
            put("inputs", inputs);
            put("errors", errors);
            put("generated_uuid", UUID.randomUUID().toString());
        }});
    }
}
