package tv.codely.apps.backoffice.frontend.controller.students;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public final class StudentsGetWebController {
    @GetMapping("/students")
    public ModelAndView index(@ModelAttribute("inputs") HashMap<String, Serializable> inputs,
    @ModelAttribute("errors") HashMap<String, List<String>> errors)  {
        return new ModelAndView("pages/students/students", new HashMap<String, Serializable>() {{
            put("title", "Students");
            put("description", "Students DinoGeek - Backoffice");
            put("inputs", inputs);
            put("errors", errors);
            put("generated_uuid", UUID.randomUUID().toString());
        }});
    }
}
