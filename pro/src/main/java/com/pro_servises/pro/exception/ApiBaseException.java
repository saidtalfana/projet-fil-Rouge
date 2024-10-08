package com.pro_servises.pro.exception;

import org.springframework.http.HttpStatus;


public abstract class ApiBaseException extends RuntimeException {

    protected ApiBaseException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}
