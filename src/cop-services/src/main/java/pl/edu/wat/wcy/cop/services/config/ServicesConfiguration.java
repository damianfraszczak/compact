/**
 *
 */
package pl.edu.wat.wcy.cop.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import pl.edu.wat.wcy.cop.services.UsernameAuditorAware;
import pl.edu.wat.wcy.cop.symbolservice.SymbolService;


@Configuration
@Import(value = PersistenceConfig.class)
@ComponentScan(basePackages = {"pl.edu.wat.wcy.cop.services"})
// Configures services settings.
public class ServicesConfiguration {

    @Bean
    public SymbolService produceSymbolService() {
        return new SymbolService();
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new UsernameAuditorAware();
    }
}
