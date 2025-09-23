package tv.codely.mooc.courses.application.create;

import src.mooc.main.tv.codely.mooc.courses.domain.CourseDuration;
import src.mooc.main.tv.codely.mooc.courses.domain.CourseId;
import src.mooc.main.tv.codely.mooc.courses.domain.CourseName;
import src.mooc.test.tv.codely.mooc.courses.domain.CourseDurationMother;
import src.mooc.test.tv.codely.mooc.courses.domain.CourseIdMother;
import src.mooc.test.tv.codely.mooc.courses.domain.CourseNameMother;
import tv.codely.mooc.courses.domain.*;

public final class CreateCourseCommandMother {
    public static CreateCourseRequest create(CourseId id, CourseName name, CourseDuration duration) {
        return new CreateCourseRequest(id.value(), name.value(), duration.value());
    }

    public static CreateCourseRequest random() {
        return create(CourseIdMother.random(), CourseNameMother.random(), CourseDurationMother.random());
    }
}
