package tv.codely.apps.backoffice.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;


@Configuration
public class BackofficeBackendServerConfiguration {

    @Bean
    public FilterRegistrationBean<BasicHttpAuthMiddleware> basicHttpAuthMiddleware() {
        FilterRegistrationBean<BasicHttpAuthMiddleware> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new BasicHttpAuthMiddleware());
        registrationBean.addUrlPatterns("/health-check");

        return registrationBean;
    }
}
