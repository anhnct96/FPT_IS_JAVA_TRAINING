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
@Constraint(validatedBy = Validator.MobileValidator.class)
@Documented
public @interface Mobile {

    String message() default "Incorrect phone number format!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
