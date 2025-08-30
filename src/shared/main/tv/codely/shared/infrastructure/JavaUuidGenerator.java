package tv.codely.shared.infrastructure;

import org.springframework.context.annotation.Profile;
import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.domain.UuidGenerator;

import java.util.UUID;

@ServiceInjectable
@Profile({"test"})
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
