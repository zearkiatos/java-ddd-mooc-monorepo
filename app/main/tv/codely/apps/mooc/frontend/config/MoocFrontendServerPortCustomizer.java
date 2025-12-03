package tv.codely.apps.mooc.frontend.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import tv.codely.shared.infrastructure.config.ParameterNotExist;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.infrastructure.config.Parameter;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;

@Component
public class MoocFrontendServerPortCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>  {
    private final Parameter param;

    public MoocFrontendServerPortCustomizer(Parameter param) {
        this.param = param;
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        try {
            factory.setPort(param.getInt("MOOC_FRONTEND_SERVER_PORT"));
        } catch (ParameterNotExist parameterNotExist) {
            parameterNotExist.printStackTrace();
        }
    }
}
