package com.pro_servises.pro.exception;

import org.springframework.http.HttpStatus;


public class NotFoundException extends ApiBaseException {

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
