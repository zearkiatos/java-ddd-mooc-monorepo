package tv.codely.apps;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import tv.codely.shared.domain.ServiceInjectable;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ServiceInjectable.class),
    value = {"tv.codely.apps.mooc", "tv.codely.mooc", "tv.codely.shared"}
)
public class MoocApplication {

}
