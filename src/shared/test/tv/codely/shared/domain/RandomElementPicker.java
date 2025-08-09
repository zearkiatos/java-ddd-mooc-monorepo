package tv.codely.shared.domain;

import java.util.Random;

public class RandomElementPicker {

    private static final Random random = new Random();

    public static <T> T from(T... elements) {
        return elements[random.nextInt(elements.length)];
    }
}
