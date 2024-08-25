package com.pro_servises.pro.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){

        super(message);
    }
}
