package football.listeners;

import football.enums.ColumnTypes;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Component
public @interface RegisterUDF {
    int arguments() default 1;
    ColumnTypes returnType();
}
