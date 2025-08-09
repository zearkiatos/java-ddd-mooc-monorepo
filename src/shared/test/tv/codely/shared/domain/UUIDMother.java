package tv.codely.shared.domain;

public final class UUIDMother {
    public static String random() {
        return java.util.UUID.randomUUID().toString();
    }
}
