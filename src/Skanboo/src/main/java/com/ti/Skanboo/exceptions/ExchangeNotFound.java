package com.ti.Skanboo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExchangeNotFound extends RuntimeException {
    
    public ExchangeNotFound(String message) {
        super(message);
    }
}
