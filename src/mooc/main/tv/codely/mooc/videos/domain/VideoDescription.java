package tv.codely.mooc.videos.domain;

import tv.codely.shared.domain.StringValueObject;

public final class VideoDescription extends StringValueObject {

    public VideoDescription(String value) {
        super(value);
    }

    private VideoDescription() {
        super("");
    }

}
