package tv.codely.shared.domain;

import java.util.regex.Pattern;

public abstract class UrlValueObject extends StringValueObject {

    public UrlValueObject(String value) {
        super(value);
        if (value != null) {
            validate(value);
        }
    }

    private static final String URL_REGEX = Pattern.compile(
            "^(?:https?://)?" +
                    "([\\w-]+\\.)+[A-Za-z]{2,}" +
                    "(?::\\d{1,5})?" +
                    "(?:[/?#]\\S*)?$",
            Pattern.CASE_INSENSITIVE).toString();

    private void validate(String value) {
        if (value == null || !value.matches(URL_REGEX)) {
            throw new IllegalArgumentException("Invalid URL: " + value);
        }
    }

    
}
