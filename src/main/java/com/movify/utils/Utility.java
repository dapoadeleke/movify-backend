package com.movify.utils;

import javax.validation.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class Utility {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private Validator validator = factory.getValidator();

    /* Validate request against the expected object */
    public void validateRequest(Object req) {
        Set<ConstraintViolation<Object>> violations = this.validator.validate(req);
        if(violations.size() > 0)
            throw new ConstraintViolationException(violations);
    }

    /*
    * Generate a slug from a string
     */
    public String slugify(String str) {
        return str.toLowerCase()
                .replace("'", "")
                .replace(":", "-")
                .replace(".", "")
                .replace(",", "")
                .replace("?", "")
                .replace("&", "")
                .replace(" ", "-");
    }


}
