package tv.codely.mooc.notifications.send_welcome_user_email;

import java.lang.annotation.Annotation;

import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;

public final class SendWelcomeUserEmailOnUserRegistered implements DomainEventSubscriber  {
    @Override
    public Class<? extends DomainEvent<?>> value() {
        return new Class[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
