package tv.codely.shared.domain;

import net.datafaker.Faker;

public final class EmailMother {
    private static final Faker faker = new Faker();

    public static String random() {
        return faker.internet().emailAddress();
    }
}
