package org.jakartaeerecipe.annotation;

import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface JavaBookTitle {

    String message() default "{org.jakartaeerecipe.annotation." +
            "message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
