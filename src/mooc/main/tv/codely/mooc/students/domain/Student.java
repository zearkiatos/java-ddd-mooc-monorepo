package tv.codely.mooc.students.domain;

import java.util.Objects;

public class Student {
    private StudentId id;
    private StudentName name;
    private StudentSurname surname;
    private StudentEmail email;

    public Student(StudentId id, StudentName name, StudentSurname surname, StudentEmail email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Default constructor for Hibernate
    protected Student() {
    }

    public StudentId id() {
        return id;
    }

    public StudentName name() {
        return name;
    }

    public StudentSurname surname() {
        return surname;
    }

    public StudentEmail email() {
        return email;
    }

    public String getId() {
        return id != null ? id.value() : null;
    }

    public void setId(String idString) {
        this.id = idString != null ? new StudentId(idString) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Student))
            return false;

        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email);
    }
}
