package com.project.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class OutOfStockException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public OutOfStockException(String message) {
        super(message);
    }
}