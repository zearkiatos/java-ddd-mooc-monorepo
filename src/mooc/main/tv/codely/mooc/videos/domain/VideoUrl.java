package tv.codely.mooc.videos.domain;

import tv.codely.shared.domain.UrlValueObject;

public final class VideoUrl extends UrlValueObject {

    public VideoUrl(String value) {
        super(value);
    }

    // Default constructor for Hibernate
    protected VideoUrl() {
        super(null);
    }

}
