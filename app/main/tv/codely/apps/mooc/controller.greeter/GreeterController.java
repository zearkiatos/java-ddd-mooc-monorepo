package tv.codely.apps.mooc.controller.greeter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class GreeterController {

    @GetMapping("/greeter")
    public String index(@RequestParam(value = "name") String name) {
        return "Hello, " + name;
    }

}
