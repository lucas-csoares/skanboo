package com.ti.Skanboo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RatingNotFound extends RuntimeException {
    
    public RatingNotFound(String message) {
        super(message);
    }
}
