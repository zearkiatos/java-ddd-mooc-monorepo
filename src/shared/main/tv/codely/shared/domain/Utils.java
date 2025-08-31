package tv.codely.shared.domain;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Utils {
    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static jsonEncode(HashMap<String, Serializable> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        }
        catch (JsonProcessingException e) {
            return "";
        }
    }

    public static HashMap<String, Serializable> jsonDecode(String body) {
        try {
            return new ObjectMapper().readValue(body, HashMap.class);
        }
        catch (IOException e) {
            return new HashMap<>();
        }
    }
}
