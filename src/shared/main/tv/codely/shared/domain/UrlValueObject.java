package tv.codely.shared.domain;

public abstract class UrlValueObject extends StringValueObject {

    public UrlValueObject(String value) {
        super(value);
        validate(value);
    }

    private static final String URL_REGEX = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";

    private void validate(String value) {
        if (value == null || !value.matches(URL_REGEX)) {
            throw new IllegalArgumentException("Invalid URL: " + value);
        }
    }
}
