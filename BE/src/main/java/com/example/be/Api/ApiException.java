package com.example.be.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
