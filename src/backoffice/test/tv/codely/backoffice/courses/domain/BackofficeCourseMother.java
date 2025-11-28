package tv.codely.backoffice.courses.domain;

import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.shared.domain.WordMother;
import tv.codely.shared.domain.UUIDMother;

public final class BackofficeCourseMother {
    public static BackofficeCourse create(String id, String name, String duration) {
        return new BackofficeCourse(id, name, duration);
    }

    public static BackofficeCourse create(String name, String duration) {
        return new BackofficeCourse(UUIDMother.random(), name, duration);
    }

    public static BackofficeCourse random() {
        return create(UUIDMother.random(), WordMother.random(), WordMother.random());
    }
}
