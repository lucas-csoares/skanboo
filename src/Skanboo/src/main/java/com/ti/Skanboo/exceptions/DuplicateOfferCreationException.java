package com.ti.Skanboo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateOfferCreationException extends RuntimeException {

    public DuplicateOfferCreationException(String message) {
        super(message);
    }
    
}
