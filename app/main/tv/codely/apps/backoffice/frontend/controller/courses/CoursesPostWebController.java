package tv.codely.apps.backoffice.frontend.controller.courses;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;

import tv.codely.shared.domain.bus.command.CommandBus;


import tv.codely.shared.domain.bus.command.CommandNotRegisteredError;




import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.mooc.courses.application.create.CreateCourseCommand;
import tv.codely.shared.infrastructure.validation.Validator;
import tv.codely.shared.infrastructure.validation.ValidationResponse;
import tv.codely.shared.infrastructure.validation.ValidatorNotExist;

@Controller
public final class CoursesPostWebController {
    private final CommandBus bus;
    private final HashMap<String, String> rules = new HashMap<String, String>() {{
        put("title", "required|not_empty|uuid");
        put("description", "required|not_empty|string");
        put("duration", "required|not_empty|string");
    }};

    public CoursesPostWebController(CommandBus bus) {
        this.bus = bus;
    }

    @PostMapping(value = "/courses", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView index(@RequestParam HashMap<String,Serializable> request, RedirectAttributes redirectAttributes) throws Exception {
        ValidationResponse validationResponse = Validator.validate(request, rules);

        return validationResponse.hasErrors() ?
            redirectToFormWithErrors(validationResponse, request, redirectAttributes) :
            createCourse(request);
    }

    private RedirectView redirectToFormWithErrors(ValidationResponse validationResponse, HashMap<String, Serializable> request, RedirectAttributes attributes) {
        attributes.addFlashAttribute("errors", validationResponse.errors());
        attributes.addFlashAttribute("request", request);

        return new RedirectView("/courses");
    }

 private RedirectView createCourse(HashMap<String, Serializable> request) throws CommandNotRegisteredError {
        bus.dispatch(new CreateCourseCommand(
            request.get("id").toString(),
            request.get("name").toString(),
            request.get("duration").toString()
        ));

        return new RedirectView("/courses");
    }

}
