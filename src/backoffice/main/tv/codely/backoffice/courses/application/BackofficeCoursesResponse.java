package tv.codely.backoffice.courses.application;

import java.util.List;

import tv.codely.shared.domain.bus.query.Response;

public final class BackofficeCoursesResponse implements Response{
    private final List<BackofficeCourseResponse> courses;

    public BackofficeCoursesResponse(List<BackofficeCourseResponse> courses) {
        this.courses = courses;
    }

    public List<BackofficeCourseResponse> courses() {
        return courses;
    }
}
