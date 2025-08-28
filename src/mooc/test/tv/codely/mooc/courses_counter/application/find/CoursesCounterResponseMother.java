package tv.codely.mooc.courses_counter.application.find;

import tv.codely.mooc.courses_counter.application.find.CoursesCounterResponse;
import tv.codely.shared.domain.IntegerMother;

final class CoursesCounterResponseMother {
    public static CoursesCounterResponse create(Integer total) {
        return new CoursesCounterResponse(total);
    }

    public static CoursesCounterResponse random() {
        return create(IntegerMother.random());
    }
}
