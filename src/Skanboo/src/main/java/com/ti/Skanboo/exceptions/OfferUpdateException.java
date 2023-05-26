package com.ti.Skanboo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class OfferUpdateException extends RuntimeException {
        
    public OfferUpdateException(String message) {
        super(message);
    }
}
