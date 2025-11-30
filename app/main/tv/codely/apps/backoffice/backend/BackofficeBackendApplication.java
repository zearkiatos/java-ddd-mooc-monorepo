package tv.codely.apps.backoffice.backend;

import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import tv.codely.apps.backoffice.backend.command.ConsumeMySqlDomainEventsCommand;
import tv.codely.shared.domain.ServiceInjectable;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ServiceInjectable.class),
    value = {"tv.codely.shared", "tv.codely.backoffice", "tv.codely.mooc", "tv.codely.apps.backoffice.backend"}
)
public class BackofficeBackendApplication {
    public static HashMap<String, Class<?>> commands() {
        return new HashMap<String, Class<?>>() {{
            put("domain-events:mysql:consume", ConsumeMySqlDomainEventsCommand.class);
        }};
    }
}
