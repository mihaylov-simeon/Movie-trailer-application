package com.moveiapp.final_exam_projectmovieapplication.util;


import jakarta.validation.*;
import org.springframework.stereotype.Component;



@Component
public class ValidationUtilsImpl implements ValidationUtils {

    private final Validator validator;

    public ValidationUtilsImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }

}
