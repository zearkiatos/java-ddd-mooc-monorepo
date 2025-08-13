package tv.codely.shared.domain;

import net.datafaker.Faker;

public final class LongTextMother {
    private static final Faker faker = new Faker();

    public static String random() {
        return faker.lorem().paragraphs(5).stream()
                .reduce((paragraph1, paragraph2) -> paragraph1 + "\n\n" + paragraph2)
                .orElse("");
    }
}
