package tv.codely.mooc.courses_counter.domain;

import tv.codely.mooc.courses_counter.domain.CoursesCounterId;

import tv.codely.shared.domain.UUIDMother;

public final class CoursesCounterIdMother {
        public static CoursesCounterId create(String value) {
        return new CoursesCounterId(value);
    }

    public static CoursesCounterId random() {
        return create(UUIDMother.random());
    }
}
