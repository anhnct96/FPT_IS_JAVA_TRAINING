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
@Constraint(validatedBy = Validator.QuantityAndAmountValidator.class)
@Documented
public @interface NumberType {

    String message() default "Number must be > 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
