package tv.codely.shared.domain;

import net.datafaker.Faker;

public final class IntegerMother {
    private static final Faker faker = new Faker();

    public static int random() {
        return faker.number().numberBetween(1, 100);
    }
}
