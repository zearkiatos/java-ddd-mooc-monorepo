package tv.codely.shared.domain;

public final class VideoUrl extends UrlValueObject {

    public VideoUrl(String value) {
        super(value);
    }

    // Default constructor for Hibernate
    protected VideoUrl() {
        super(null);
    }

}
