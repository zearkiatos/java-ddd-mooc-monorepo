package tv.codely.shared.domain;

public abstract class EmailValueObject extends StringValueObject {

    public EmailValueObject(String value) {
        super(value);
        validate(value);
    }

    // Default constructor for Hibernate
    protected EmailValueObject() {
        super();
    }

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private void validate(String value) {
        if (!value.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email format: " + value);
        }
    }


}
