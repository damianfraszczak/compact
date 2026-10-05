package pl.edu.wat.wcy.cop.app.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
// Configures cop security settings.
public class CopSecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${auth.use_login}")
    private boolean useForms;
    @Value("${auth.active}")
    private boolean authActive;
    @Autowired
    private CesarAuthenticationProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.headers().disable().csrf().disable().cors().disable();
        if (authActive) {
            http.authorizeRequests()
                    .antMatchers("/cop.html", "/cop.html/**", "/cop/**").authenticated()
//                .and().formLogin().defaultSuccessUrl("/cop.html")
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                    //.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and().addFilterBefore(new CopAuthFilter(), BasicAuthenticationFilter.class);
            if (useForms) {
                http.formLogin().defaultSuccessUrl("/cop.html");
            }
        }

    }


}
