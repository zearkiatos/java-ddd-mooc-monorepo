package tv.codely.mooc.students.application.create;

public class CreateStudentRequest {
    private String id;
    private String name;
    private String surname;
    private String email;

    public CreateStudentRequest(String id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateStudentRequest)) return false;

        CreateStudentRequest that = (CreateStudentRequest) o;

        return id.equals(that.id) &&
               name.equals(that.name) &&
               surname.equals(that.surname) &&
               email.equals(that.email);
    }
}
