package tv.codely.backoffice.auth.application.authenticate;

import java.util.Optional;
import tv.codely.backoffice.auth.domain.AuthPassword;
import tv.codely.backoffice.auth.domain.AuthRepository;
import tv.codely.backoffice.auth.domain.AuthUser;
import tv.codely.backoffice.auth.domain.AuthUsername;
import tv.codely.backoffice.auth.domain.exceptions.InvalidAuthCredentials;
import tv.codely.backoffice.auth.domain.exceptions.InvalidAuthUsername;


public final class UserAuthenticator {
    private final AuthRepository repository;

    public UserAuthenticator(AuthRepository repository) {
        this.repository = repository;
    }

    public void authenticatr(AuthUsername username, AuthPassword password) {
        Optional<AuthUser> auth = respository.search(username);


        ensureUserExist(auth, username);
        ensureCredentialsAreValid(auth.get(), password);

    }
}
