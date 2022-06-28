package vn.fis.training.ordermanagement.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Validator {
    static class NameValidator implements ConstraintValidator<Name, String> {
        @Override
        public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
            return name.length() > 10 && name.length() < 100;
        }
    }

    static class MobileValidator implements ConstraintValidator<Mobile, String> {
        @Override
        public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
            return mobile.matches("^[0]( *[\\d] *){9}$");
        }
    }

    static class QuantityAndAmountValidator implements ConstraintValidator<NumberType, Long> {
        @Override
        public boolean isValid(Long numberType, ConstraintValidatorContext constraintValidatorContext) {
            return numberType > 0L;
        }
    }
}
