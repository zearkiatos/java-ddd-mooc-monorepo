package tv.codely.mooc.students.application.create;

import tv.codely.shared.domain.bus.command.Command;

public final class CreateStudentCommand implements Command{
    private String id;
    private String name;
    private String surname;
    private String email;

    public CreateStudentCommand(String id, String name, String surname, String email) {
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
        if (!(o instanceof CreateStudentCommand)) return false;

        CreateStudentCommand that = (CreateStudentCommand) o;

        return id.equals(that.id) &&
               name.equals(that.name) &&
               surname.equals(that.surname) &&
               email.equals(that.email);
    }
}
