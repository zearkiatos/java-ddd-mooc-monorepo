package tv.codely.apps.backoffice.frontend.controller.courses;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Map;

import tv.codely.shared.domain.validation.ValidationResponse;
import tv.codely.shared.domain.validation.Validator;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.mooc.courses.application.create.CreateCourseCommand;

public final class CoursesPostWebController {
    private final CommandBus bus;
    private final HashMap<String, String> rules = new HashMap<String, String>() {{
        put("title", "required|not_empty|uuid");
        put("description", "required|not_empty|string");
        put("duration", "required|not_empty|string");
    }}

    public CoursesPostWebController(CommandBus bus) {
        this.bus = bus;
    }

    @PostMapping("/courses", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView index(@RequestParam Map<String,String> request, RedirectAttributes redirectAttributes) throws Exception {
        ValidationResponse validationResponse = Validator.validate(request, rules);

        return validationResponse.hasErrors() ?
            redirectToFormWithErrors(request, validationResponse.errors(), redirectAttributes) :
            createCourse(request);
    }

}
