package tv.codely.apps.mooc.controller.students;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tv.codely.mooc.students.application.create.CreateStudentRequest;
import tv.codely.mooc.students.application.create.StudentCreator;

@RestController
public final class StudentsPutController {
    private final StudentCreator creator;

    public StudentsPutController(StudentCreator creator) {
        this.creator = creator;
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<Integer> create(@PathVariable String id, @RequestBody Request request) {
        // try {
            creator.create(new CreateStudentRequest(id, request.name(), request.surname(), request.email()));
        // } catch (Exception e) {
        //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

final class Request {
    private String name;
    private String surname;
    private String email;

    String name() {
        return name;
    }

    String surname() {
        return surname;
    }

    String email() {
        return email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
