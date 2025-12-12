package tv.codely.backoffice.auth.infrastructure.persistence;

import java.util.HashMap;
import java.util.Optional;
import tv.codely.shared.domain.ServiceInjectable;


@ServiceInjectable
public final class InMemoryAuthRepository implements AuthRepository {
    private final HashMap<AuthUsername, AuthPassword> users = new HashMap<AuthUsername, AuthPassword>() {{
        put(new AuthUsername("admin"), new AuthPassword("admin123"));
        put(new AuthUsername("user"), new AuthPassword("user123"));
    }};

    @Override
    public Optional<AuthUser> search(AuthUsername username) {
        return users.containsKey(username)
            ? Optional.of(new AuthUser(username, users.get(username)))
            : Optional.empty();
    }
}
