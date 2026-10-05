/**
 *
 */
package pl.edu.wat.wcy.cop.app.server;

import com.google.gwt.logging.server.RemoteLoggingServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.edu.wat.wcy.cop.app.server.security.CopSecurityConfig;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.decisionsupport.DecisionSupportConfig;
import pl.edu.wat.wcy.cop.services.config.ServicesConfiguration;


@Configuration()
@Import(value = {ServicesConfiguration.class, DecisionSupportConfig.class, CopSecurityConfig.class})
// Configures web settings.
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new RemoteLoggingServiceImpl(), GwtEndpoints.GWT_REMOTE_LOGGING + "/*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(logRequestsInterceptor());
    }

    @Bean()
    @Scope(scopeName = "prototype")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
//    }

    @Bean
    public LogRequestsInterceptor logRequestsInterceptor() {
        return new LogRequestsInterceptor();
    }

}
