package pl.edu.wat.wcy.cop.app.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;



@SpringBootApplication
@EnableConfigurationProperties
@Configuration
// @EnableAutoConfiguration
// Represents server application.
public class ServerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
