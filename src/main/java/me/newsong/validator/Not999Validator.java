package me.newsong.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Not999Validator implements ConstraintValidator<Not999, Long> {

    @Override
    public void initialize(Not999 arg0) {

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == 999) {
            return false;
        } else {
            return true;
        }
    }

}
