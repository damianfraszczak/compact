package pl.edu.wat.wcy.cop.app.client.jaxb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface JAXBBindings {
    Class<?> value();

    Class<?>[] objects() default {};
}
