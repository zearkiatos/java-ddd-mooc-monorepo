package tv.codely.backoffice.auth.application.authenticate;

import java.util.Optional;

import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.bus.command.CommandHandler;

@ServiceInjectable
public final class AuthenticateUserCommandHandler implements CommandHandler<AuthenticateUserCommand> {
    private final AuthRepository repository;

    public UserAuthenticator(AuthRepository repository) {
        this.repository = repository;
    }

    public void authenticate(AuthUsername username, AuthPassword password) {
        Optional<AuthUser> auth = repository.search(username);

        ensureUserExist(auth, username);
        ensureCredentialsAreValid(auth.get(), password);
    }

    private void ensureUserExist(Optional<AuthUser> auth, AuthUsername username) {
        if (!auth.isPresent()) {
            throw new InvalidAuthUsername(username);
        }
    }

    private void ensureCredentialsAreValid(AuthUser auth, AuthPassword password) {
        if (!auth.passwordMatches(password)) {
            throw new InvalidAuthCredentials(auth.username());
        }
    }
}

