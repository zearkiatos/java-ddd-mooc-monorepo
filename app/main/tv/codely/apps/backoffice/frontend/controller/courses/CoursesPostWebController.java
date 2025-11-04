package tv.codely.apps.backoffice.frontend.controller.courses;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger log = LoggerFactory.getLogger(CoursesPostWebController.class);
    private final CommandBus bus;
    private final HashMap<String, String> rules = new HashMap<String, String>() {{
        put("id", "required|not_empty|uuid");
        put("name", "required|not_empty|string");
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

        log.info("Validation errors found: {}", validationResponse.errors());

        return new RedirectView("/courses");
    }

 private RedirectView createCourse(HashMap<String, Serializable> request) throws CommandNotRegisteredError {
        String id = request.get("id").toString();
        try {
            bus.dispatch(new CreateCourseCommand(
                id,
                request.get("name").toString(),
                request.get("duration").toString()
            ));
            log.info("Course created successfully id={}", id);
        } catch (CommandNotRegisteredError e) {
            log.error("Command not registered while creating course id={}", id, e);
            throw e;
        } catch (RuntimeException e) {
            log.error("Unexpected error while creating course id={}", id, e);
            throw e;
        }

        return new RedirectView("/courses");
    }

}
