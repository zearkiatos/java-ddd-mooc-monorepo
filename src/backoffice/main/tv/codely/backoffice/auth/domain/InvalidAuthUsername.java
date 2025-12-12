package tv.codely.backoffice.auth.domain;

public class InvalidAuthUsername extends RuntimeException {
    public InvalidAuthUsername(AuthUsername username) {
        super(String.format("The user <%s> does not exist", username.value()));
}
