package vn.fis.training.ordermanagement.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = Validator.NameValidator.class)
@Documented
public @interface Name {

    String message() default "Incorrect name format/length";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
