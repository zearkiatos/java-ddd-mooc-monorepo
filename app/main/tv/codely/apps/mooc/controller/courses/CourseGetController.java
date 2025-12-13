package tv.codely.mooc.courses.controller;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.shared.domain.bus.query.QueryHandlerExecutionError;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.infrastructure.spring.ApiController;
import tv.codely.shared.domain.DomainError;

@RestController
public final class CourseGetController extends ApiController {
    public CourseGetController(
        QueryBus queryBus,
        CommandBus commandBus
    ) {
        super(queryBus, commandBus);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<HashMap<String, Serializable>> index(@PathVariable String id) throws QueryHandlerExecutionError, QueryNotRegisteredError {
        CourseResponse course = ask(new FindCourseQuery(id));

        return ResponseEntity.ok().body(new HashMap<String, Serializable>() {{
            put("id", course.id());
            put("name", course.name());
            put("duration", course.duration());
        }});
    }

    @Override
    protected HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<Class<? extends DomainError>, HttpStatus>() {{
            put(CourseNotExist.class, HttpStatus.NOT_FOUND);
        }};
    }
}
