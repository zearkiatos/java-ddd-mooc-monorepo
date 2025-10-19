package tv.codely.shared.infrastructure.validation.validators;

import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Pattern;

public final class UuidValidator implements FieldValidator {

    private static final Pattern UUID_PATTERN = Pattern.compile(
        "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
        Pattern.CASE_INSENSITIVE
    );
    @Override
    public Boolean isValid(String fieldName, HashMap<String, Serializable> fields) {
        Pattern uuidPattern = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
        Serializable value = fields.get(fieldName);
        if (value == null) return false;

        String s = value.toString();
        if (s == null) return false;

        s = s.trim();
        if (s.isEmpty()) return false;

        return UUID_PATTERN.matcher(s).matches();
    }

    @Override
    public String errorMessage(String fieldName) {
        return String.format("The field <%s> is not a valid UUID", fieldName);
    }
}
