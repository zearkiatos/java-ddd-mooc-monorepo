package tv.codely.shared.domain;

import net.datafaker.Faker;

public final class UrlMother {
    private static final Faker faker = new Faker();

    public static String random() {
        return faker.internet().url();
    }
}
