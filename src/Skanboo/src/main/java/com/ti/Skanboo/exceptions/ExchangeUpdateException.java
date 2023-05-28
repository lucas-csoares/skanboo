package com.ti.Skanboo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class ExchangeUpdateException extends RuntimeException {
    
    public ExchangeUpdateException(String message) {
        super(message);
    }
}
