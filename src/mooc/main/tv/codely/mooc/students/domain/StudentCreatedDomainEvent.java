package tv.codely.mooc.students.domain;

import java.util.Objects;

import tv.codely.shared.domain.bus.event.DomainEvent;

public final class StudentCreatedDomainEvent extends DomainEvent<StudentCreatedDomainEvent> {
    private final String name;
    private final String surname;
    private final String email;

    public StudentCreatedDomainEvent(String aggregateId, String name, String surname, String email) {
        super(aggregateId);

        this.name    = name;
        this.surname = surname;
        this.email   = email;
    }

    public StudentCreatedDomainEvent(
        String aggregateId,
        String eventId,
        String occurredOn,
        String name,
        String surname,
        String email
    ) {
        super(aggregateId, eventId, occurredOn);

        this.name    = name;
        this.surname = surname;
        this.email   = email;
    }

    @Override
    public String eventName() {
        return "student.created";
    }

    @Override
    protected java.util.HashMap<String, java.io.Serializable> toPrimitives() {
        return new java.util.HashMap<String, java.io.Serializable>() {{
            put("name", name);
            put("surname", surname);
            put("email", email);
        }};
    }

    @Override
    protected StudentCreatedDomainEvent fromPrimitives(
        String aggregateId,
        java.util.HashMap<String, java.io.Serializable> body,
        String eventId,
        String occurredOn
    ) {
        return new StudentCreatedDomainEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("name"),
            (String) body.get("surname"),
            (String) body.get("email")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentCreatedDomainEvent that = (StudentCreatedDomainEvent) o;
        return name.equals(that.name) && surname.equals(that.surname) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email);
    }
}
